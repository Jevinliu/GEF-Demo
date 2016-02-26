package wbhgef.actions;

import java.util.HashMap;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import wbhgef.model.Node;
import wbhgef.wizard.RenameWizard;

public class RenameAction extends SelectionAction {

	public RenameAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	@Override
	protected void init() {
		setText("Rename...");
		setToolTipText("Rename");
		setId(ActionFactory.RENAME.getId());
		ImageDescriptor icon = PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_UP);
		setImageDescriptor(icon);
		setEnabled(false);
	}

	@Override
	protected boolean calculateEnabled() {
		Command cmd = createRenameCommand("");
		if (cmd == null) {
			return false;
		}
		return true;
	}

	public Command createRenameCommand(String name) {
		Request renameReq = new Request("rename");
		HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newName", name);
		renameReq.setExtendedData(reqData);

		// 由 editpart 获得 command
		if (getSelectedObjects().isEmpty()) {
			return null;
		}
		EditPart object = (EditPart) getSelectedObjects().get(0);
		Command cmd = object.getCommand(renameReq); // 包装
													// ,很重要命令的创建实在策略里边，而请求的创建是在Action里边，有一个动作就会有相应的request
		return cmd;
	}

	// 由 Action -> command(request) ->
	// 运行时创建command。此时的command已经封装了 request
	public void run() {
		Node node = getSelectedNode();
		RenameWizard wizard = new RenameWizard(node.getName());
		WizardDialog dialog = new WizardDialog(getWorkbenchPart().getSite()
				.getShell(), wizard);
		dialog.create();
		dialog.getShell().setSize(400, 320);
		dialog.setTitle("Rename wizard");
		dialog.setMessage("Rename");
		if (dialog.open() == WizardDialog.OK) {
			String name = wizard.getRenameValue();
			execute(createRenameCommand(name));
		}
	}

	private Node getSelectedNode() {
		List objects = getSelectedObjects();
		if (objects.isEmpty()) {
			return null;
		}
		if (!(objects.get(0) instanceof EditPart)) {
			return null;
		}
		EditPart part = (EditPart) objects.get(0);
		return (Node) part.getModel();

	}
}
