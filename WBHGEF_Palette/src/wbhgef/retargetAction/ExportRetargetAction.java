package wbhgef.retargetAction;

import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RetargetAction;

import wbhgef.actions.helper.DAActionConstants;

public class ExportRetargetAction extends RetargetAction {

	public ExportRetargetAction() {
		super(null, null);
		setText("Export");
        setId(DAActionConstants.EXPORT_IMG);
        setToolTipText("Export image");
        setActionDefinitionId(DAActionConstants.EXPORT_IMG);
	}

}
