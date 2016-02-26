package wbhgef.actions;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

/**
 * 上下文菜单提供器
 * 
 * @author software
 *
 */
public class AppContextMenuProvider extends ContextMenuProvider {

	private ActionRegistry actionRegistry;

	public AppContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		setActionRegistry(registry);
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {

		GEFActionConstants.addStandardActionGroups(menu);
		IAction deleteAction = getActionRegistry().getAction(
				ActionFactory.DELETE.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, deleteAction);
		IAction undoAction = getActionRegistry().getAction(
				ActionFactory.UNDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, undoAction);
		IAction renameAction = getActionRegistry().getAction(
				ActionFactory.RENAME.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, renameAction);
	}

	private void setActionRegistry(ActionRegistry registry) {
		this.actionRegistry = registry;
	}

	private ActionRegistry getActionRegistry() {
		return this.actionRegistry;
	}
}
