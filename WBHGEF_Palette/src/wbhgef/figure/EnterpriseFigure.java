package wbhgef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class EnterpriseFigure extends Figure {

	private Label labelName = new Label();
	private Label labelAddress = new Label();
	private Label labelCapital = new Label();
	private XYLayout layout;

	public EnterpriseFigure() {
		layout = new XYLayout();
		setLayoutManager(layout);// 所有具有后代的图案必须声明一个LayoutManager。该LayoutManager负责放置并调整子图案的大小。
		labelName.setForegroundColor(ColorConstants.blue);
		add(labelName);
		setConstraint(labelName, new Rectangle(5, 5, -1, -1));
		labelAddress.setForegroundColor(ColorConstants.lightBlue);
		add(labelAddress);
		setConstraint(labelAddress, new Rectangle(5, 17, -1, -1));
		labelCapital.setForegroundColor(ColorConstants.lightBlue);
		add(labelCapital);
		setConstraint(labelCapital, new Rectangle(5, 30, -1, -1));
		setForegroundColor(ColorConstants.black);
		setBackgroundColor(ColorConstants.white);
		setBorder(new LineBorder(5));
		setOpaque(true);
	}

	public void setLayout(Rectangle rect) {
		setBounds(rect);
	}

	public void setName(String text) {
		labelName.setText(text);
	}

	public void setAddress(String text) {
		labelAddress.setText(text);
	}

	public void setCapital(int capital) {
		labelCapital.setText("Capital:" + capital);
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		System.out.println("bounds: " + getBounds());
	}

}
