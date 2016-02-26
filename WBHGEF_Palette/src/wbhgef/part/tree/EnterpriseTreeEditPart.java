package wbhgef.part.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import wbhgef.model.Enterprise;
import wbhgef.model.Node;

public class EnterpriseTreeEditPart extends AppAbstractTreeEditPart {

	@Override
	protected List<Node> getModelChildren() {
		// TODO Auto-generated method stub
		return ((Enterprise) getModel()).getChildrenArray();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
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
