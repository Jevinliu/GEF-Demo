package wbhgef.retargetAction;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RetargetAction;

import wbhgef.actions.helper.DAActionConstants;

public class RadioRetargetAction extends RetargetAction {

	 public RadioRetargetAction() {
	        super(null, null,IAction.AS_RADIO_BUTTON);
	        setText("Radio...");
	        setId(DAActionConstants.RADIO);
	        setToolTipText("Radio");
	        setImageDescriptor( PlatformUI.getWorkbench()
					.getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_UP_HOVER));
	        setActionDefinitionId(DAActionConstants.RADIO);
	    }
}
