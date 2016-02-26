package wbhgef.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import wbhgef.editpolicy.AppDeletePolicy;
import wbhgef.editpolicy.AppEditLayoutPolicy;
import wbhgef.editpolicy.AppRenamePolicy;
import wbhgef.figure.ServiceFigure;
import wbhgef.model.Node;
import wbhgef.model.Service;

/**
 * editpart 是MVC中的control层， view层是Draw2D，
 * 
 * @author software
 *
 */
public class ServicePart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		IFigure figure = new ServiceFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());

	}

	@Override
	public void refreshVisuals() {
		ServiceFigure figure = (ServiceFigure) getFigure();

		Service model = (Service) getModel(); // 链接M 和 V
		figure.setName(model.getName());
		figure.setEtage(model.getEtage());
		figure.setLayout(model.getLayout());
	}

	@Override
	protected List<Node> getModelChildren() {
		return ((Service) getModel()).getChildrenArray();
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
		if (evt.getPropertyName().equals(Service.PROPERTY_COLOR))
			refreshVisuals();
		if (evt.getPropertyName().equals(Service.PROPERTY_FLOOR))
			refreshVisuals();
	}
}
