package wbhgef.retargetAction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.actions.RetargetAction;

/**
 * 
 * DDMRetargetAction
 * <p>
 * 下拉式toolbar的父类retargetAction
 * </p>
 * 
 * @see
 */
public class DDMRetargetAction extends RetargetAction implements IMenuCreator {
    
    private MenuManager dropDownMenuMgr;
    private RetargetAction selfAction;
    private List<RetargetAction> dropDownActions = new ArrayList<RetargetAction>();
    
    public DDMRetargetAction(int style){
    	 super(null, null, IAction.AS_DROP_DOWN_MENU | style);
         setMenuCreator(this);
    }
    
    public DDMRetargetAction() {
        super(null, null, IAction.AS_DROP_DOWN_MENU);
        setMenuCreator(this);
    }
    
    /**
     * 添加Action的下拉菜单的action
     * 
     * @param retargetAction
     */
    public void addDropDownAction(RetargetAction retargetAction) {
        if (!this.dropDownActions.contains(retargetAction))
            this.dropDownActions.add(retargetAction);
    }
    
    private void createDropDownMenuMgr() {
        if (dropDownMenuMgr == null) {
            dropDownMenuMgr = new MenuManager();
            if (!dropDownActions.isEmpty())
                for (RetargetAction ra : dropDownActions)
                    dropDownMenuMgr.add(ra);
        }
    }
    
    @Override public Menu getMenu(Control parent) {
        createDropDownMenuMgr();
        return dropDownMenuMgr.createContextMenu(parent);
    }
    
    @Override public Menu getMenu(Menu parent) {
        return null;
    }
    
    @Override public void dispose() {
        if (dropDownMenuMgr != null) {
            dropDownMenuMgr.dispose();
            dropDownMenuMgr = null;
        }
    }

	public RetargetAction getSelfAction() {
		return selfAction;
	}

	public void setSelfAction(RetargetAction selfAction) {
		this.selfAction = selfAction;
	}
}
