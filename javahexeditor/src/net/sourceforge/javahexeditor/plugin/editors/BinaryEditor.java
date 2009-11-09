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
import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sourceforge.javahexeditor.BinaryContent;
import net.sourceforge.javahexeditor.HexTexts;
import net.sourceforge.javahexeditor.Manager;
import net.sourceforge.javahexeditor.plugin.BinaryEditorPlugin;

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
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.WorkbenchPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;


public class BinaryEditor extends EditorPart implements ISelectionProvider {


class MyAction extends Action {
	String myId = null;
	MyAction(String id) {
		myId = id;
	}
	public void run() {
		if (myId.equals(ActionFactory.UNDO.getId()))
			getManager().doUndo();
		else if (myId.equals(ActionFactory.REDO.getId()))
			getManager().doRedo();
		else if (myId.equals(ActionFactory.CUT.getId()))
			getManager().doCut();
		else if (myId.equals(ActionFactory.COPY.getId()))
			getManager().doCopy();
		else if (myId.equals(ActionFactory.PASTE.getId()))
			getManager().doPaste();
		else if (myId.equals(ActionFactory.DELETE.getId()))
			getManager().doDelete();
		else if (myId.equals(ActionFactory.SELECT_ALL.getId()))
			getManager().doSelectAll();
		else if (myId.equals(ActionFactory.FIND.getId()))
			getManager().doFind();
	}
}


static final String textSavingFilePleaseWait = "Saving file, please wait";

private Manager manager = null;
IContentOutlinePage outlinePage = null;
IPropertyChangeListener preferencesChangeListener = null;
Set selectionListeners = null;  // of ISelectionChangedListener


public BinaryEditor() {
	super();
System.out.println("BinEditor constructor:"+this);System.out.flush();
}


public void addSelectionChangedListener(ISelectionChangedListener listener) {
System.out.println("BinaryEditor addSelectionChangedListener()");
	if (listener == null) return;
	
	if (selectionListeners == null) {
		selectionListeners = new HashSet();
	}
	selectionListeners.add(listener);
}


public void createPartControl(Composite parent) {
System.out.println("Start createPartControl()");System.out.flush();
	getManager().setTextFont(BinaryEditorPlugin.getFontData());
	getManager().setFindReplaceLists(BinaryEditorPlugin.getFindReplaceFindList(),
			BinaryEditorPlugin.getFindReplaceReplaceList());
	manager.createEditorPart(parent);
	FillLayout fillLayout = new FillLayout();
	parent.setLayout(fillLayout);

	String charset = null;
	IEditorInput unresolved = getEditorInput();
	File systemFile = null;
	IFile localFile = null;
	if (unresolved instanceof IPathEditorInput) {
		localFile = ((FileEditorInput)unresolved).getFile();
	} else if (unresolved instanceof ILocationProvider) {
		ILocationProvider location = (ILocationProvider)unresolved;
		IWorkspaceRoot rootWorkspace = ResourcesPlugin.getWorkspace().getRoot();
		localFile = rootWorkspace.getFile(location.getPath(location));		
	} else {
		URI uri = inputForVersion3_3(unresolved);
		if (uri != null) {
			IFile[] files = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(uri);
			if (files.length != 0) {
				localFile = files[0];
			} else {
				systemFile = new File(uri);				
			}
		} else {
			systemFile = null;
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
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
	setPartName(systemFile.getName());

	System.out.println("BinaryEditor file    : " + systemFile.getName());
	System.out.println("BinaryEditor charset : " + manager.getContent().getCharset());

	// Register any global actions with the site's IActionBars.
	IActionBars bars = getEditorSite().getActionBars();
	String id = ActionFactory.UNDO.getId();
	bars.setGlobalActionHandler(id, new MyAction(id));
	id = ActionFactory.REDO.getId();
	bars.setGlobalActionHandler(id, new MyAction(id));
	id = ActionFactory.CUT.getId();
	bars.setGlobalActionHandler(id, new MyAction(id));
	id = ActionFactory.COPY.getId();
	bars.setGlobalActionHandler(id, new MyAction(id));
	id = ActionFactory.PASTE.getId();
	bars.setGlobalActionHandler(id, new MyAction(id));
	id = ActionFactory.DELETE.getId();
	bars.setGlobalActionHandler(id, new MyAction(id));
	id = ActionFactory.SELECT_ALL.getId();
	bars.setGlobalActionHandler(id, new MyAction(id));
	id = ActionFactory.FIND.getId();
	bars.setGlobalActionHandler(id, new MyAction(id));
	
	manager.addListener(new Listener() {
		public void handleEvent(Event event) {
			firePropertyChange(PROP_DIRTY);
			updateActionsStatus();
		}
	});

	bars.updateActionBars();
	
	preferencesChangeListener = new IPropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent event) {
			if (PreferencesPage.preferenceFontData.equals(event.getProperty()))
				manager.setTextFont((FontData)event.getNewValue());
		}
	};
	IPreferenceStore store = BinaryEditorPlugin.getDefault().getPreferenceStore();
	store.addPropertyChangeListener(preferencesChangeListener);

	manager.addLongSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
System.out.println("BinaryEditor, long selection");
			if (selectionListeners == null) return;
			
			long[] longSelection = HexTexts.getLongSelection(e);
			SelectionChangedEvent event = new SelectionChangedEvent(BinaryEditor.this,
					new StructuredSelection(new Object[] {
							new Long(longSelection[0]), new Long(longSelection[1])}));
			for (Iterator iterator = selectionListeners.iterator(); iterator.hasNext();) {
				((ISelectionChangedListener)iterator.next()).selectionChanged(event);
			}
		}
	});
	getSite().getPage().addSelectionListener(//getSite().getPage().getActiveEditor().getSite().getId(), 
			new ISelectionListener() {
				public void selectionChanged(IWorkbenchPart part, ISelection selection) {
					if ("net.sourceforge.javahexeditor".equals(part.getSite().getId())) return;
/*					Object[] startEnd = ((StructuredSelection)selection).toArray();
System.out.println("BinaryEditor received selection from:"+part.getSite().getRegisteredName()+", "+
startEnd[0]+","+startEnd[1]);*/
				}
			});
//	getSite().setSelectionProvider(this);
System.out.println("End createPartControl()");System.out.flush();
}


/**
 * Removes preferences-changed listener
 * @see WorkbenchPart#dispose()
 */
public void dispose() {
	IPreferenceStore store = BinaryEditorPlugin.getDefault().getPreferenceStore();
	store.removePropertyChangeListener(preferencesChangeListener);
}


/**
 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
 */
public void doSave(IProgressMonitor monitor) {
	monitor.beginTask(textSavingFilePleaseWait, IProgressMonitor.UNKNOWN);
	boolean successful = getManager().saveFile();
	monitor.done();
	if (!successful) {
		manager.showErrorBox(getEditorSite().getShell());
	}
}


/**
 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
 */
public void doSaveAs() {
	saveToFile(false);
}


public Object getAdapter(Class required) {
System.out.println("getAdapter(" + required + ")");System.out.flush();
	Object result = null;
	if (IContentOutlinePage.class.isAssignableFrom(required)) {
		if (outlinePage == null) {
			outlinePage = getOutlinePage();
		}
		result = outlinePage;
	} else if (BinaryContent.class.isAssignableFrom(required)) {
		result = getManager().getContent();
	} else {
		result = super.getAdapter(required);
	}
System.out.println("returns " + result);
	return result;
}


/**
 * Getter for the manager instance.
 * @return the manager
 */
public Manager getManager() {
	if (manager == null)
		manager = new Manager();
	
	return manager;
}


IContentOutlinePage getOutlinePage() {
	IExtensionRegistry registry = Platform.getExtensionRegistry();
	IExtensionPoint point = registry.getExtensionPoint("net.sourceforge.javahexeditor.outline");
	if (point == null) return null;;
	IExtension[] extensions = point.getExtensions();
	if (extensions.length == 0) return null;
	IConfigurationElement[] elements = extensions[0].getConfigurationElements();
	String className = null;
	for (int i = 0; i < elements.length; ++i) {
		if ("outline".equals(elements[i].getName())) {
			className = elements[i].getAttribute("class");
			break;
		}
	}
	
	Bundle aBundle = Platform.getBundle(extensions[0].getNamespace());
	IContentOutlinePage result = null;
	if (aBundle != null) {
		try {
			aBundle.start();
		} catch (BundleException e) {
			return null;
		}
		try {
			// throws IllegalAccessException, InstantiationException, ClassNotFoundException
			result = (IContentOutlinePage)aBundle.loadClass(className).newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	return result;
}


public ISelection getSelection() {
	long[] longSelection = getManager().getSelection();
System.out.println("BinaryEditor getSelection(), returns "+longSelection[0]+", "+longSelection[1]);
	return new StructuredSelection(new Object[]
		{new Long(longSelection[0]), new Long(longSelection[1])});
}


boolean implementsInterface(IEditorInput input, String interfaceName) {
	Class[] classes = input.getClass().getInterfaces();
	for (int i = 0; i < classes.length; ++i) {
		if (interfaceName.equals(classes[i].getName()))
			return true;
	}
	
	return false;
}


public void init(IEditorSite site, final IEditorInput input) throws PartInitException {
System.out.println("BinaryEditor.init() starts:"+this+", selection provider:"+
site.getSelectionProvider());

	setSite(site);
	if (!(input instanceof IPathEditorInput) &&
		!(input instanceof ILocationProvider) &&
		(!implementsInterface(input, "org.eclipse.ui.IURIEditorInput") ||  // since 3.3 only
		inputForVersion3_3(input) == null)) {
System.out.println("Class:"+input.getClass());
System.out.println("Superclass:"+input.getClass().getSuperclass());
Class[]classes=input.getClass().getInterfaces();
for(int i=0;i<classes.length;++i)System.out.println("Interface:"+classes[i]);
		throw new PartInitException("Input is not a file");
	}
	setInput(input);
	// when opening an external file the workbench (Eclipse 3.1) calls HexEditorActionBarContributor.
	// MyStatusLineContributionItem.fill() before HexEditorActionBarContributor.setActiveEditor()
	// but we need an editor to fill the status bar.
	site.getActionBarContributor().setActiveEditor(this);
	site.setSelectionProvider(this);
System.out.println("BinEditor.init() ends");System.out.flush();
}


private URI inputForVersion3_3(IEditorInput input) {
	URI result = null;
	try {
		Class aClass = input.getClass();
		// throws NoSuchMethodException
		Method uriMethod = aClass.getMethod("getURI", null);
		// throws IllegalAccessException, InvocationTargetException
		result = (URI)uriMethod.invoke(input, null);
	} catch (Throwable e) {
		return null;
	}
	
	return result;
}


public boolean isDirty() {
	return getManager().isDirty();
}


public boolean isSaveAsAllowed() {
	return true;
}


public void removeSelectionChangedListener(ISelectionChangedListener listener) {
System.out.println("BinaryEditor removeSelectionChangedListener()");
	if (selectionListeners != null) {
		selectionListeners.remove(listener);
	}
}


void saveToFile(final boolean selection) {
	final File file = getManager().showSaveAsDialog(getEditorSite().getShell(), selection);
	if (file == null) return;
		
	IRunnableWithProgress runnable = new IRunnableWithProgress() {
		public void run(IProgressMonitor monitor) {
			monitor.beginTask(textSavingFilePleaseWait, IProgressMonitor.UNKNOWN);
			boolean successful = false;
			if (selection)
				successful = manager.doSaveSelectionAs(file);
			else
				successful = manager.saveAsFile(file);
			monitor.done();
			if (successful && !selection) {
				setPartName(file.getName());
				firePropertyChange(PROP_DIRTY);
			}
			if (!successful)
				manager.showErrorBox(getEditorSite().getShell());
		}
	};
	ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(getEditorSite().getShell());
	try {
		monitorDialog.run(false, false, runnable);
	} catch (InvocationTargetException e) {
		throw new RuntimeException(e);
	} catch (InterruptedException e) {
		throw new RuntimeException(e);
	}
}


public void setFocus() {
	// useless. It is called before ActionBarContributor.setActiveEditor() so focusing is done there
}


public void setSelection(ISelection selection) {
System.out.println("BinaryEditor setSelection()");
	if (selection.isEmpty()) return;
	StructuredSelection aSelection = (StructuredSelection)selection;
	long start = ((Long)aSelection.getFirstElement()).longValue();
	long end = start;
	if (aSelection.size() > 1) {
		end = ((Long)aSelection.toArray()[1]).longValue();
	}
	getManager().setSelection(start, end);
}


/**
 * Updates the status of actions: enables/disables them depending on whether there is text selected
 * and whether inserting or overwriting. Undo/redo actions enabled/disabled as well.
 */
public void updateActionsStatus() {
	boolean textSelected = getManager().isTextSelected();
	boolean lengthModifiable = textSelected && !manager.isOverwriteMode();
	IActionBars bars = getEditorSite().getActionBars();
	IAction action = bars.getGlobalActionHandler(ActionFactory.UNDO.getId());
	if (action != null) action.setEnabled(manager.canUndo());
	
	action = bars.getGlobalActionHandler(ActionFactory.REDO.getId());
	if (action != null) action.setEnabled(manager.canRedo());
	
	action = bars.getGlobalActionHandler(ActionFactory.CUT.getId());
	if (action != null) action.setEnabled(lengthModifiable);
	
	action = bars.getGlobalActionHandler(ActionFactory.COPY.getId());
	if (action != null) action.setEnabled(textSelected);
	
	action = bars.getGlobalActionHandler(ActionFactory.DELETE.getId());
	if (action != null) action.setEnabled(lengthModifiable);

	bars.updateActionBars();
}
}
