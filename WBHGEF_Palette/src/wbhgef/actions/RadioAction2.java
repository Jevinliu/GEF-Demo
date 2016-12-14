package wbhgef.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPart;

import wbhgef.actions.helper.DAActionConstants;

public class RadioAction2 extends SelectionAction{

	public RadioAction2(IWorkbenchPart part) {
		super(part,IAction.AS_RADIO_BUTTON);
		setId(DAActionConstants.RADIO2);
		setText("Radio2");
		setActionDefinitionId(DAActionConstants.RADIO2);
	}
	
	@Override
	protected boolean calculateEnabled() {
		return true;
	}
	
	@Override
	public void run() {
		System.out.println("radio action2....");
	}
}
