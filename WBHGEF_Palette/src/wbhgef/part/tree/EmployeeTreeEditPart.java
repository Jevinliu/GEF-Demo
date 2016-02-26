package wbhgef.part.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import wbhgef.editpolicy.AppDeletePolicy;
import wbhgef.model.Employee;
import wbhgef.model.Node;

public class EmployeeTreeEditPart extends AppAbstractTreeEditPart {

	@Override
	protected List<Node> getModelChildren() {
		// TODO Auto-generated method stub
		return ((Employee) getModel()).getChildrenArray();
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
	}

	public void refreshVisuals() {
		Employee model = (Employee) getModel();
		setWidgetText(model.getName() + " " + model.getPrenom());
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_DEF_VIEW));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE))
			refreshChildren();
		if (evt.getPropertyName().equals(Employee.PROPERTY_FIRSTNAME))
			refreshVisuals();
	}
}
