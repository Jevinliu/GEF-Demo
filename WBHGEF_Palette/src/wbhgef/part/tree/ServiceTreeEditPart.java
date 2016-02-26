package wbhgef.part.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import wbhgef.editpolicy.AppDeletePolicy;
import wbhgef.editpolicy.AppRenamePolicy;
import wbhgef.model.Node;
import wbhgef.model.Service;

public class ServiceTreeEditPart extends AppAbstractTreeEditPart {

	@Override
	protected List<Node> getModelChildren() {
		return ((Service) getModel()).getChildrenArray();
	}

	@Override
	protected void refreshVisuals() {

		Service model = (Service) getModel();
		setWidgetText(model.getName());
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_OBJ_ELEMENT));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) {
			refreshVisuals();
		}

		if (evt.getPropertyName().equals(Service.PROPERTY_COLOR))
			refreshVisuals();
		if (evt.getPropertyName().equals(Service.PROPERTY_FLOOR))
			refreshVisuals();

	}

	@Override
	protected void createEditPolicies() {
		// 只添加对树视图有作用的添加和删除的策略
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}

}
