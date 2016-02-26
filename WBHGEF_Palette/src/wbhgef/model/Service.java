package wbhgef.model;

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class Service extends Node {

	private Color color;
	private int etage; // floor
	public static final String PROPERTY_COLOR = "ServiceColor";
	public static final String PROPERTY_FLOOR = "ServiceFloor";

	public int getEtage() {
		return etage;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	private Color createRandomColor() {
		return new Color(null,
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128);
	}

	public Service() {
		this.color = createRandomColor();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		Color oldColor = this.color;
		this.color = color;
		getListeners().firePropertyChange(PROPERTY_COLOR, oldColor, this.color);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Service srv = new Service();
		srv.setColor(this.color);
		srv.setEtage(this.etage);
		srv.setName(this.getName());
		srv.setParent(this.getParent());
		srv.setLayout(new Rectangle(getLayout().x + 10, getLayout().y + 10,
				getLayout().width, getLayout().height));
		Iterator<Node> it = this.getChildrenArray().iterator();

		while (it.hasNext()) {
			Node node = it.next();
			if (node instanceof Employee) {
				Employee child = (Employee) node;
				Node clone = (Node) child.clone();
				srv.addChild(clone);
				clone.setLayout(child.getLayout());
			}
		}
		return srv;
	}

}
