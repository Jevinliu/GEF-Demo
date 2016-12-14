package wbhgef.retargetAction;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RetargetAction;

import wbhgef.actions.helper.DAActionConstants;

public class RadioRetargetAction2 extends RetargetAction {

	 public RadioRetargetAction2() {
	        super(null, null,IAction.AS_RADIO_BUTTON);
	        setText("Radio2...");
	        setId(DAActionConstants.RADIO2);
	        setToolTipText("Radio2");
	        setImageDescriptor( PlatformUI.getWorkbench()
					.getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
	        setActionDefinitionId(DAActionConstants.RADIO2);
	    }
}
