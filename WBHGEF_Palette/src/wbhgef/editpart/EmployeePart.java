package wbhgef.editpart;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import wbhgef.editpolicy.AppDeletePolicy;
import wbhgef.figure.EmployeeFigure;
import wbhgef.model.Employee;
import wbhgef.model.Node;

public class EmployeePart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		IFigure figure = new EmployeeFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
	}

	@Override
	protected void refreshVisuals() {
		EmployeeFigure figure = (EmployeeFigure) getFigure();
		Employee model = (Employee) getModel();
		figure.setName(model.getName());
		figure.setFirstName(model.getPrenom());
		figure.setLayout(model.getLayout());
	}

	public List<Node> getModelChildren() {
		return new ArrayList<Node>();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) {
			refreshVisuals();
		}
		if (evt.getPropertyName().equals(Employee.PROPERTY_FIRSTNAME))
			refreshVisuals();

	}
}
