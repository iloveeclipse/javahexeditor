package net.sourceforge.javahexeditor.plugin.actions;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;

public class FileOpenAction implements IObjectActionDelegate {

	private File[] resource = null;
	private IStructuredSelection currentSelection;

	/**
	 * Constructor for EasyExploreAction.
	 */
	public FileOpenAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {

		if (!isEnabled()) {
			MessageDialog.openInformation(
				new Shell(),
				"Logfile Viewer",
				"Wrong Selection");
			return;
		}

		for (int i=0;i<resource.length;i++) {

			if (resource[i] == null)
				continue;

			if (resource[i].isDirectory()) {
			}else {
				IEditorInput editorInput = null;
				IFile file= ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(resource[i].getPath()));

	            if (file != null ) {
	            	editorInput = new FileEditorInput(file);
	            } else {
	            	IFileStore file_store = EFS.getLocalFileSystem().getStore(new Path(resource[i].getPath()));
	            	editorInput = new FileStoreEditorInput(file_store);
	            }

				IWorkbenchWindow window=PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				IWorkbenchPage page = window.getActivePage();
				try {
					page.openEditor(editorInput, "net.sourceforge.javahexeditor",true,org.eclipse.ui.IWorkbenchPage.MATCH_INPUT | org.eclipse.ui.IWorkbenchPage.MATCH_ID);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	    currentSelection = selection instanceof IStructuredSelection ? (IStructuredSelection)selection : null;
	}

	protected boolean isEnabled()
	{
		boolean enabled = false;
		if (currentSelection != null)
		{
			Object[] selectedObjects = currentSelection.toArray();
			if (selectedObjects.length >= 1)
			{
				resource = new File[selectedObjects.length];
				for (int i=0;i<selectedObjects.length;i++) {
					resource[i] = getResource(selectedObjects[i]);
					if (resource != null)
						enabled=true;
				}
			}
		}
		return enabled;
	}

	protected File getResource(Object object) {
		if (object instanceof IFile) {
			return ((IFile) object).getLocation().toFile();
		}
		if (object instanceof File) {
			return (File) object;
		}
		if (object instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) object;
			IFile ifile = (IFile) adaptable.getAdapter(IFile.class);
			if (ifile != null) {
				return ifile.getLocation().toFile();
			}
			IResource ires = (IResource) adaptable.getAdapter(IResource.class);
			if (ires != null) {
				return ires.getLocation().toFile();
			}
			/*
			if (adaptable instanceof PackageFragment
					&& ((PackageFragment) adaptable).getPackageFragmentRoot() instanceof JarPackageFragmentRoot) {
				return getJarFile(((PackageFragment) adaptable)
						.getPackageFragmentRoot());
			} else if (adaptable instanceof JarPackageFragmentRoot) {
				return getJarFile(adaptable);
			} else if (adaptable instanceof FileStoreEditorInput) {
				URI fileuri = ((FileStoreEditorInput) adaptable).getURI();
				return new File(fileuri.getPath());
			}
			*/
			File file = (File) adaptable.getAdapter(File.class);
			if (file != null) {
				return file;
			}
		}
		return null;
	}
	/*
	protected File getJarFile(IAdaptable adaptable) {
		JarPackageFragmentRoot jpfr = (JarPackageFragmentRoot) adaptable;
		File resource = (File) jpfr.getPath().makeAbsolute().toFile();
		if (!((File) resource).exists()) {
			File projectFile =
				new File(
					jpfr
						.getJavaProject()
						.getProject()
						.getLocation()
						.toOSString());
			resource = new File(projectFile.getParent() + resource.toString());
		}
		return resource;
	}
	*/
}
