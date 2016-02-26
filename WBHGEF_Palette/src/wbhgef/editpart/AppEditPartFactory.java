package wbhgef.editpart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import wbhgef.model.Employee;
import wbhgef.model.Enterprise;
import wbhgef.model.Service;

public class AppEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		AbstractGraphicalEditPart part = null;
		if (model instanceof Enterprise) {
			part = new EnterprisePart();
		} else if (model instanceof Service) {
			part = new ServicePart();
		} else if (model instanceof Employee) {
			part = new EmployeePart();
		}
		if (part != null) {
			part.setModel(model);
		}
		return part;
	}

}
