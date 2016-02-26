package wbhgef.part.tree;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import wbhgef.model.Employee;
import wbhgef.model.Enterprise;
import wbhgef.model.Service;

public class AppTreeEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		// TODO Auto-generated method stub
		EditPart part = null;
		if (model instanceof Enterprise) {
			part = new EnterpriseTreeEditPart();
		} else if (model instanceof Service) {
			part = new ServiceTreeEditPart();
		} else if (model instanceof Employee) {
			part = new EmployeeTreeEditPart();
		}
		if (part != null) {
			part.setModel(model);
		}
		return part;
	}

}
