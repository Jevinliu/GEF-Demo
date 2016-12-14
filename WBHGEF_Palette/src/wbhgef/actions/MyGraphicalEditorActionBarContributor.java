package wbhgef.actions;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;

import wbhgef.actions.helper.DAActionConstants;
import wbhgef.retargetAction.DDRadioRetargetAction;
import wbhgef.retargetAction.ExportRetargetAction;
import wbhgef.retargetAction.RadioRetargetAction;
import wbhgef.retargetAction.RadioRetargetAction2;

/**
 * toolbar 助推器
 * 
 * @author software
 *
 */
public class MyGraphicalEditorActionBarContributor extends ActionBarContributor {

	@Override
	protected void buildActions() {
		// TODO Auto-generated method stub

		IWorkbenchWindow iww = getPage().getWorkbenchWindow();
		// 初始化Actions
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());
		addRetargetAction(new ZoomInRetargetAction()); // 放大
		addRetargetAction(new ZoomOutRetargetAction()); // 缩小
		addRetargetAction((RetargetAction) ActionFactory.COPY.create(iww));
		addRetargetAction((RetargetAction) ActionFactory.PASTE.create(iww));
		
		DDRadioRetargetAction ddRetAction = new DDRadioRetargetAction();
		RadioRetargetAction rra = new RadioRetargetAction();
		addRetargetAction(rra);
		ddRetAction.addDropDownAction(rra);
		ddRetAction.setSelfAction(rra);
		RadioRetargetAction2 rra2 = new RadioRetargetAction2();
		ddRetAction.addDropDownAction(rra2);
		addRetargetAction(rra2);
		addRetargetAction(ddRetAction);
		addRetargetAction((RetargetAction) ActionFactory.PRINT.create(iww));
		
		addRetargetAction(new ExportRetargetAction());
	}

	@Override
	protected void declareGlobalActionKeys() {
	}

	@SuppressWarnings("deprecation")
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
		toolBarManager.add(getAction(ActionFactory.REDO.getId()));
		toolBarManager.add(getAction(ActionFactory.DELETE.getId()));
		toolBarManager.add(new Separator());
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_IN));
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_OUT));
		toolBarManager.add(new ZoomComboContributionItem(getPage())); // 添加缩放比例下拉框
		toolBarManager.add(getAction(ActionFactory.COPY.getId()));
		toolBarManager.add(getAction(ActionFactory.PASTE.getId()));
		toolBarManager.add(getAction(DAActionConstants.RADIO));
//		toolBarManager.add(getAction(DAActionConstants.RADIO2));
		toolBarManager.add(getAction(GEFActionConstants.PRINT));
		toolBarManager.add(getAction(DAActionConstants.EXPORT_IMG));

	}

	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		// super.contributeToMenu(menuManager);
	}

}
