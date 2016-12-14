package wbhgef.actions;

import java.io.File;
import java.io.IOException;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.WorkbenchPart;

import wbhgef.actions.helper.DAActionConstants;
import wbhgef.editor.MyGraphicalEditor;
import wbhgef.others.ExportImageUtil;

public class ExportImgAction extends SelectionAction {

	public ExportImgAction(WorkbenchPart part) {
		super(part);
		setText("Export");
        setId(DAActionConstants.EXPORT_IMG);
        setToolTipText("Export image");
        setActionDefinitionId(DAActionConstants.EXPORT_IMG);
	}
	
	@Override
	protected boolean calculateEnabled() {
		if(getWorkbenchPart() instanceof MyGraphicalEditor)
			return true;
		return false;
	}

	@Override
	public void run() {
		File file = new File("C:\\Users\\software\\Desktop\\test.PNG");
		try {
		if(!file.exists())
				file.createNewFile();
		ExportImageUtil.export((IEditorPart) getWorkbenchPart(), file, SWT.IMAGE_PNG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
