package wbhgef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class EmployeeFigure extends Figure {

	public static final int EMPLOYE_FIGURE_DEFWIDTH = 40;
	public static final int EMPLOYE_FIGURE_DEFHEIGHT = 40;
	private Label labelName = new Label();
	private Label labelFirstName = new Label();
	private double PI = 3.1415926;

	public EmployeeFigure() {
		DICOMInfoLayout layout = new DICOMInfoLayout();
		setLayoutManager(layout);
		labelFirstName.setForegroundColor(ColorConstants.black);
		Transform tf = new Transform();
		tf.setRotation(PI / 2);
		add(labelFirstName, DICOMInfoLayout.BOTTOMCENTER);
		labelName.setForegroundColor(ColorConstants.darkGray);
		add(labelName, DICOMInfoLayout.BOTTOMCENTER);
		Point tl = labelName.getBounds().getTopRight();
		Point tl_tf = tf.getTransformed(tl);
		System.out.println("tl: " + tl + ", tl_tf: " + tl_tf);
		setForegroundColor(ColorConstants.darkGray);
		setBackgroundColor(ColorConstants.lightGray);
		setBorder(new LineBorder(1));
		setOpaque(true);
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		graphics.pushState();
		graphics.setForegroundColor(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		graphics.rotate(90);

		Dimension dim = getBounds().getSize();
		Point origin = getBounds().getTopLeft();
		int length = 0;
		int stride = 9;
		float smallLength = 0;
		length = dim.height - 1;

		if (length < 10) {
			stride = 0;
		} else if (length < 50) {
			stride = 1;
		} else if (length < 100) {
			stride = 4;
		}

		if (stride != 0) {
			smallLength = length / (float) (stride + 1);
		}

		Point bottomLeft = new Point(origin.x, origin.y + getBounds().height / 2);
		Point bottomRight = new Point(origin.x + length, origin.y + getBounds().height / 2);
		graphics.drawLine(bottomLeft, bottomRight);
		graphics.drawLine(bottomLeft, new Point(bottomLeft.x, bottomLeft.y - 10));
		graphics.drawLine(bottomRight, new Point(bottomRight.x, bottomLeft.y - 10));
		float offset = bottomLeft.x + smallLength;
		for (int i = 0; i < stride; i++) {
			Point one = new Point((int) offset, bottomLeft.y);
			graphics.drawLine(one, new Point(one.x, one.y - 5));
			offset += smallLength;
		}

		graphics.popState();
	}

	public void setName(String text) {
		labelName.setText(text);
	}

	public void setFirstName(String text) {
		labelFirstName.setText(text);
	}

	public void setLayout(Rectangle rect) {
		getParent().setConstraint(this, rect);
	}

}
