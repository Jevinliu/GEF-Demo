package wbhgef.editor;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import wbhgef.model.NodeCreationFactory;

public class MyTemplateTransferDropTargetListener extends
		TemplateTransferDropTargetListener {

	public MyTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}

	@Override
	protected CreationFactory getFactory(Object template) {
		if (template instanceof NodeCreationFactory)
			return (CreationFactory) template; // 在拖拽时获取工厂
		return null;
	}
}
