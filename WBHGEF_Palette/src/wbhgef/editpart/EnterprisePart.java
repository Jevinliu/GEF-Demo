package wbhgef.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import wbhgef.editpolicy.AppEditLayoutPolicy;
import wbhgef.figure.EnterpriseFigure;
import wbhgef.model.Enterprise;
import wbhgef.model.Node;

public class EnterprisePart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		IFigure figure = new EnterpriseFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
	}

	@Override
	protected void refreshVisuals() {
		EnterpriseFigure figure = (EnterpriseFigure) getFigure();
		Enterprise model = (Enterprise) getModel();
		figure.setName(model.getName());
		figure.setAddress(model.getAddress());
		figure.setCapital(model.getCapital());
	}

	public List<Node> getModelChildren() {
		return ((Enterprise) getModel()).getChildrenArray();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) {
			refreshVisuals();
		}
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME))
			refreshVisuals();
		if (evt.getPropertyName().equals(Enterprise.PROPERTY_CAPITAL))
			refreshVisuals();
	}
}
