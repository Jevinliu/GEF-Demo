package wbhgef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import wbhgef.model.Service;

/**
 * view层，使用draw2D实现
 * 
 * @author software
 *
 */
public class ServiceFigure extends Figure {

	private Label labelName = new Label();
	private Label labelEtage = new Label();
	public static final int SERVICE_FIGURE_DEFWIDTH = 250;
	public static final int SERVICE_FIGURE_DEFHEIGHT = 150; // 图形的相关属性设置在figure上，这样可以保证模型的纯洁性

	public ServiceFigure() {
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
		labelName.setForegroundColor(ColorConstants.darkGray);
		add(labelName, ToolbarLayout.ALIGN_CENTER);
		setConstraint(labelName, new Rectangle(5, 17, -1, -1));
		labelEtage.setForegroundColor(ColorConstants.black);
		add(labelEtage, ToolbarLayout.ALIGN_CENTER);
		setConstraint(labelEtage, new Rectangle(5, 5, -1, -1));
		// setForegroundColor(new Color(null,
		// (new Double(Math.random() * 128)).intValue(), (new Double(
		// Math.random() * 128)).intValue(), (new Double(
		// Math.random() * 128)).intValue()));
		// setBackgroundColor(new Color(null,
		// (new Double(Math.random() * 128)).intValue() + 128,
		// (new Double(Math.random() * 128)).intValue() + 128,
		// (new Double(Math.random() * 128)).intValue() + 128));
		Service service = new Service();
		setForegroundColor(ColorConstants.black);
		setBackgroundColor(service.getColor());
		setBorder(new LineBorder(1));
		setOpaque(true);
	}

	public void setName(String text) {
		labelName.setText(text);
	}

	public void setEtage(int etage) {
		labelEtage.setText("Etage:" + etage);
	}

	public void setLayout(Rectangle rect) {
		getParent().setConstraint(this, rect); // 设置子figure在父figure中的位置

	}
	
}
