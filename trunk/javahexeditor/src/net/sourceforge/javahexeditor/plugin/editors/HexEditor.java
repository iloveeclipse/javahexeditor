/*
 * javahexeditor, a java hex editor
 * Copyright (C) 2006, 2009 Jordi Bergenthal, pestatije(-at_)users.sourceforge.net
 * The official javahexeditor site is sourceforge.net/projects/javahexeditor
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package net.sourceforge.javahexeditor.plugin.editors;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import net.sourceforge.javahexeditor.BinaryContent;
import net.sourceforge.javahexeditor.BinaryContent.RangeSelection;
import net.sourceforge.javahexeditor.Manager;
import net.sourceforge.javahexeditor.Preferences;
import net.sourceforge.javahexeditor.TextUtility;
import net.sourceforge.javahexeditor.Texts;
import net.sourceforge.javahexeditor.plugin.HexEditorPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

public final class HexEditor extends EditorPart implements ISelectionProvider {

    private static class MyAction extends Action {
	private Manager manager;
	private String myId;

	public MyAction(Manager manager, String id) {
	    if (manager == null) {
		throw new IllegalArgumentException(
			"Parameter 'manager' must not be null.");
	    }
	    if (id == null) {
		throw new IllegalArgumentException(
			"Parameter 'id' must not be null.");
	    }
	    this.manager = manager;
	    myId = id;
	}

	@Override
	public void run() {
	    if (myId.equals(ActionFactory.UNDO.getId()))
		manager.doUndo();
	    else if (myId.equals(ActionFactory.REDO.getId()))
		manager.doRedo();
	    else if (myId.equals(ActionFactory.CUT.getId()))
		manager.doCut();
	    else if (myId.equals(ActionFactory.COPY.getId()))
		manager.doCopy();
	    else if (myId.equals(ActionFactory.PASTE.getId()))
		manager.doPaste();
	    else if (myId.equals(ActionFactory.DELETE.getId()))
		manager.doDelete();
	    else if (myId.equals(ActionFactory.SELECT_ALL.getId()))
		manager.doSelectAll();
	    else if (myId.equals(ActionFactory.FIND.getId()))
		manager.doFind();
	}
    }

    public static final String ID = "net.sourceforge.javahexeditor";

    private Manager manager;
    private IContentOutlinePage outlinePage;
    private IPropertyChangeListener preferencesChangeListener;
    Set<ISelectionChangedListener> selectionListeners;

    private IStatusLineManager statusLineManager;

    public HexEditor() {
	super();
    }

    @Override
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
	System.out
		.println("HexEditor addSelectionChangedListener()"
			+ listener
			+ ", "
			+ ((selectionListeners == null) ? 0
				: selectionListeners.size()));
	if (listener == null) {
	    return;
	}

	if (selectionListeners == null) {
	    selectionListeners = new HashSet<ISelectionChangedListener>();
	}
	selectionListeners.add(listener);
    }

    @Override
    public void createPartControl(Composite parent) {

	statusLineManager = getEditorSite().getActionBars()
		.getStatusLineManager();

	HexEditorPlugin plugin = HexEditorPlugin.getDefault();
	getManager().setTextFont(HexEditorPreferences.getFontData());
	manager.setFindReplaceHistory(plugin.getFindReplaceHistory());
	manager.createEditorPart(parent);
	FillLayout fillLayout = new FillLayout();
	parent.setLayout(fillLayout);

	String charset = null;
	IEditorInput unresolved = getEditorInput();
	File systemFile = null;
	IFile localFile = null;
	if (unresolved instanceof FileEditorInput) {
	    localFile = ((FileEditorInput) unresolved).getFile();
	} else if (unresolved instanceof IPathEditorInput) { // eg.
	    // FileInPlaceEditorInput
	    IPathEditorInput file = (IPathEditorInput) unresolved;
	    systemFile = file.getPath().toFile();
	} else if (unresolved instanceof ILocationProvider) {
	    ILocationProvider location = (ILocationProvider) unresolved;
	    IWorkspaceRoot rootWorkspace = ResourcesPlugin.getWorkspace()
		    .getRoot();
	    localFile = rootWorkspace.getFile(location.getPath(location));
	} else if (unresolved instanceof IURIEditorInput) {
	    URI uri = ((IURIEditorInput) unresolved).getURI();
	    if (uri != null) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile[] files = root.findFilesForLocationURI(uri);

		if (files.length != 0) {
		    localFile = files[0];
		} else {
		    systemFile = new File(uri);
		}
	    }
	}
	// charset
	if (localFile != null) {
	    systemFile = localFile.getLocation().toFile();
	    try {
		charset = localFile.getCharset(true);
	    } catch (CoreException e1) {
		e1.printStackTrace();
	    }
	}
	// open file
	try {
	    manager.openFile(systemFile, charset);
	} catch (IOException ex) {
	    statusLineManager.setErrorMessage(ex.getMessage());
	    systemFile = null;
	}

	if (systemFile != null) {
	    setPartName(systemFile.getName());
	} else {
	    setPartName(Texts.EMPTY);
	}

	// Register any global actions with the site's IActionBars.
	IActionBars bars = getEditorSite().getActionBars();
	String id = ActionFactory.UNDO.getId();
	bars.setGlobalActionHandler(id, new MyAction(manager, id));
	id = ActionFactory.REDO.getId();
	bars.setGlobalActionHandler(id, new MyAction(manager, id));
	id = ActionFactory.CUT.getId();
	bars.setGlobalActionHandler(id, new MyAction(manager, id));
	id = ActionFactory.COPY.getId();
	bars.setGlobalActionHandler(id, new MyAction(manager, id));
	id = ActionFactory.PASTE.getId();
	bars.setGlobalActionHandler(id, new MyAction(manager, id));
	id = ActionFactory.DELETE.getId();
	bars.setGlobalActionHandler(id, new MyAction(manager, id));
	id = ActionFactory.SELECT_ALL.getId();
	bars.setGlobalActionHandler(id, new MyAction(manager, id));
	id = ActionFactory.FIND.getId();
	bars.setGlobalActionHandler(id, new MyAction(manager, id));

	manager.addListener(new Listener() {
	    @Override
	    @SuppressWarnings("synthetic-access")
	    public void handleEvent(Event event) {
		firePropertyChange(PROP_DIRTY);
		updateActionsStatus();
	    }
	});

	bars.updateActionBars();

	preferencesChangeListener = new IPropertyChangeListener() {
	    @Override
	    public void propertyChange(PropertyChangeEvent event) {
		if (Preferences.FONT_DATA.equals(event.getProperty())) {
		    manager.setTextFont((FontData) event.getNewValue());
		}
	    }
	};
	IPreferenceStore store = plugin.getPreferenceStore();
	store.addPropertyChangeListener(preferencesChangeListener);

	manager.addLongSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		System.out.println("HexEditor, long selection");
		if (selectionListeners == null) {
		    return;
		}

		long[] longSelection = manager.getLongSelection(e);
		SelectionChangedEvent event = new SelectionChangedEvent(
			HexEditor.this, new StructuredSelection(new Object[] {
				new Long(longSelection[0]),
				new Long(longSelection[1]) }));
		for (ISelectionChangedListener listener : selectionListeners) {
		    listener.selectionChanged(event);
		}
	    }
	});
	getSite().getPage().addSelectionListener(// getSite().getPage().getActiveEditor().getSite().getId(),
		new ISelectionListener() {
		    @Override
		    public void selectionChanged(IWorkbenchPart part,
			    ISelection selection) {
			if ("net.sourceforge.javahexeditor".equals(part
				.getSite().getId()))
			    return;
			/*
			 * Object[] startEnd =
			 * ((StructuredSelection)selection).toArray();
			 * System.out
			 * .println("HexEditor received selection from:"
			 * +part.getSite().getRegisteredName()+", "+
			 * startEnd[0]+","+startEnd[1]);
			 */
		    }
		});
	// getSite().setSelectionProvider(this);
    }

    @Override
    public void dispose() {
	IPreferenceStore store = HexEditorPlugin.getDefault()
		.getPreferenceStore();
	store.removePropertyChangeListener(preferencesChangeListener);
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
	monitor.beginTask(Texts.EDITOR_MESSAGE_SAVING_FILE_PLEASE_WAIT,
		IProgressMonitor.UNKNOWN);
	try {
	    getManager().saveFile();
	} catch (IOException ex) {
	    statusLineManager.setErrorMessage(ex.getMessage());
	}
	monitor.done();
    }

    @Override
    public void doSaveAs() {
	saveToFile(false);
    }

    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") Class required) {
	Object result = null;
	if (IContentOutlinePage.class.isAssignableFrom(required)) {
	    if (outlinePage == null) {
		outlinePage = getOutlinePage();
	    }
	    result = outlinePage;
	} else if (BinaryContent.class.isAssignableFrom(required)) {
	    result = getManager().getContent();
	} else if (Manager.class.isAssignableFrom(required)) {
	    result = getManager();
	} else {
	    result = super.getAdapter(required);
	}
	System.out.println("getAdapter(" + required + ") = " + result);
	System.out.flush();

	return result;
    }

    /**
     * Getter for the manager instance.
     * 
     * @return the manager
     */
    public Manager getManager() {
	if (manager == null)
	    manager = new Manager();

	return manager;
    }

    IContentOutlinePage getOutlinePage() {
	IExtensionRegistry registry = Platform.getExtensionRegistry();
	IExtensionPoint point = registry
		.getExtensionPoint("net.sourceforge.javahexeditor.outline");
	if (point == null) {
	    return null;
	}

	IExtension[] extensions = point.getExtensions();
	if (extensions.length == 0) {
	    return null;
	}
	IConfigurationElement[] elements = extensions[0]
		.getConfigurationElements();
	String className = null;
	for (int i = 0; i < elements.length; ++i) {
	    if ("outline".equals(elements[i].getName())) {
		className = elements[i].getAttribute("class");
		break;
	    }
	}

	Bundle aBundle = Platform.getBundle(extensions[0]
		.getNamespaceIdentifier());
	IContentOutlinePage result = null;
	if (aBundle != null) {
	    try {
		aBundle.start();
	    } catch (BundleException e) {
		return null;
	    }
	    try {
		// throws IllegalAccessException, InstantiationException,
		// ClassNotFoundException
		result = (IContentOutlinePage) aBundle.loadClass(className)
			.newInstance();
	    } catch (Exception e) {
		return null;
	    }
	}

	return result;
    }

    @Override
    public ISelection getSelection() {
	RangeSelection rangeSelection = getManager().getSelection();
	System.out.println("HexEditor getSelection(), returns "
		+ rangeSelection.toString());
	return new StructuredSelection(new Object[] {
		new Long(rangeSelection.start), new Long(rangeSelection.end) });
    }

    boolean implementsInterface(IEditorInput input, String interfaceName) {
	Class<?>[] classes = input.getClass().getInterfaces();
	for (int i = 0; i < classes.length; ++i) {
	    if (interfaceName.equals(classes[i].getName()))
		return true;
	}

	return false;
    }

    @Override
    public void init(IEditorSite site, final IEditorInput input)
	    throws PartInitException {
	System.out.println("HexEditor.init() starts:" + this
		+ ", selection provider:" + site.getSelectionProvider());

	setSite(site);
	if (!(input instanceof IPathEditorInput)
		&& !(input instanceof ILocationProvider)
		&& (!(input instanceof IURIEditorInput))) {
	    throw new PartInitException("Input '" + input.toString()
		    + "'is not a file");
	}
	setInput(input);
	// when opening an external file the workbench (Eclipse 3.1) calls
	// HexEditorActionBarContributor.
	// MyStatusLineContributionItem.fill() before
	// HexEditorActionBarContributor.setActiveEditor()
	// but we need an editor to fill the status bar.
	site.getActionBarContributor().setActiveEditor(this);
	site.setSelectionProvider(this);
    }

    @Override
    public boolean isDirty() {
	return getManager().isDirty();
    }

    @Override
    public boolean isSaveAsAllowed() {
	return true;
    }

    @Override
    public void removeSelectionChangedListener(
	    ISelectionChangedListener listener) {
	System.out.println("HexEditor removeSelectionChangedListener()");
	if (selectionListeners != null) {
	    selectionListeners.remove(listener);
	}
    }

    void saveToFile(final boolean selection) {
	final File file = getManager().showSaveAsDialog(
		getEditorSite().getShell(), selection);
	if (file == null) {
	    return;
	}

	IRunnableWithProgress runnable = new IRunnableWithProgress() {
	    @Override
	    public void run(IProgressMonitor monitor) {
		saveToFile(file, selection, monitor);
	    }
	};
	ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(
		getEditorSite().getShell());
	try {
	    monitorDialog.run(false, false, runnable);
	} catch (InvocationTargetException ex) {
	    throw new RuntimeException(ex);
	} catch (InterruptedException ex) {
	    throw new RuntimeException(ex);
	}
    }

    void saveToFile(File file, boolean selection, IProgressMonitor monitor) {
	monitor.beginTask(Texts.EDITOR_MESSAGE_SAVING_FILE_PLEASE_WAIT,
		IProgressMonitor.UNKNOWN);
	try {
	    if (selection) {
		manager.doSaveSelectionAs(file);
	    } else {
		manager.saveAsFile(file);
	    }
	} catch (IOException ex) {
	    monitor.done();
	    statusLineManager.setErrorMessage(ex.getMessage());
	    return;
	}
	monitor.done();
	if (!selection) {
	    setPartName(file.getName());
	    firePropertyChange(PROP_DIRTY);
	}

	statusLineManager.setMessage(TextUtility.format(
		Texts.EDITOR_MESSAGE_FILE_SAVED, file.getAbsolutePath()));
    }

    @Override
    public void setFocus() {
	// useless. It is called before ActionBarContributor.setActiveEditor()
	// so focusing is done there
    }

    @Override
    public void setSelection(ISelection selection) {
	System.out.println("HexEditor setSelection()");
	if (selection.isEmpty()) {
	    return;
	}
	StructuredSelection aSelection = (StructuredSelection) selection;
	long[] startEnd = (long[]) aSelection.getFirstElement();
	long start = startEnd[0];
	long end = start;
	if (startEnd.length > 1) {
	    end = startEnd[1];
	}
	if (aSelection.size() > 1) {
	    startEnd = (long[]) aSelection.toArray()[1];
	    end = startEnd[0];
	    if (startEnd.length > 1) {
		end = startEnd[1];
	    }
	}
	getManager().setSelection(new RangeSelection(start, end));
    }

    /**
     * Updates the status of actions: enables/disables them depending on whether
     * there is text selected and whether inserting or overwriting is avtive.
     * Undo/redo actions are enabled/disabled as well.
     */
    public void updateActionsStatus() {
	boolean textSelected = getManager().isTextSelected();
	boolean lengthModifiable = textSelected && !manager.isOverwriteMode();
	IActionBars bars = getEditorSite().getActionBars();
	IAction action = bars
		.getGlobalActionHandler(ActionFactory.UNDO.getId());
	if (action != null) {
	    action.setEnabled(manager.canUndo());
	}

	action = bars.getGlobalActionHandler(ActionFactory.REDO.getId());
	if (action != null) {
	    action.setEnabled(manager.canRedo());
	}

	action = bars.getGlobalActionHandler(ActionFactory.CUT.getId());
	if (action != null) {
	    action.setEnabled(lengthModifiable);
	}

	action = bars.getGlobalActionHandler(ActionFactory.COPY.getId());
	if (action != null) {
	    action.setEnabled(textSelected);
	}

	action = bars.getGlobalActionHandler(ActionFactory.DELETE.getId());
	if (action != null) {
	    action.setEnabled(lengthModifiable);
	}

	bars.updateActionBars();
    }
}
