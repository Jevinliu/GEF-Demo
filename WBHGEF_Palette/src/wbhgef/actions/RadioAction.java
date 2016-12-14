package wbhgef.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPart;

import wbhgef.actions.helper.DAActionConstants;

public class RadioAction extends SelectionAction{

	public RadioAction(IWorkbenchPart part) {
		super(part,IAction.AS_RADIO_BUTTON);
		setId(DAActionConstants.RADIO);
		setText("Radio");
		setActionDefinitionId(DAActionConstants.RADIO);
	}
	
	@Override
	protected boolean calculateEnabled() {
		return true;
	}
	
	@Override
	public void run() {
		System.out.println("radio action");
	}
}
