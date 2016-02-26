package wbhgef.model;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

public class DAPropertySourceAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		AbstractEditPart part = (AbstractEditPart) adaptableObject;
		Object model = part.getModel();
		// check if model is already of the desired adapter type
		if (adapterType.isInstance(model)) {
			return model;
		}
		// check if model is adaptable and does provide an adapter of the
		// desired type
		if (model instanceof IAdaptable) {
			Object adapter = ((IAdaptable) model).getAdapter(adapterType);
			if (adapter != null) {
				return adapter;
			}
		}
		// fall back to platform's adapter manager
		return Platform.getAdapterManager().getAdapter(model, adapterType);
	}

	public Class[] getAdapterList() {
		return new Class[] { IPropertySource.class };
	}

}
