package wbhgef.figure;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

class DICOMInfoLayout extends AbstractHintLayout {

	/**
	 * Constants to be used as a constraint for child figures
	 */
	public static final Integer CENTER = new Integer(PositionConstants.CENTER);
	public static final Integer TOP = new Integer(PositionConstants.TOP);
	public static final Integer BOTTOM = new Integer(PositionConstants.BOTTOM);
	public static final Integer LEFT = new Integer(PositionConstants.LEFT);
	public static final Integer RIGHT = new Integer(PositionConstants.RIGHT);
	public static final Integer TOPLEFT = new Integer(TOP + LEFT);
	public static final Integer TOPRIGHT = new Integer(TOP + RIGHT);
	public static final Integer BOTTOMLEFT = new Integer(BOTTOM + LEFT);
	public static final Integer BOTTOMRIGHT = new Integer(BOTTOM + RIGHT);
	public static final Integer BOTTOMCENTER = new Integer(BOTTOM + CENTER);
	private IFigure center, left, top, bottom, right, topLeft, topRight, bottomLeft, bottomRight, bottomCenter;
	private int vMargin = 0, hMargin = 0;

	@Override
	public void layout(IFigure container) {
		Rectangle area = container.getClientArea();
		Rectangle rect = new Rectangle();

		Dimension areaSize = area.getSize();
		Dimension childSize;

		Point origin = area.getTopLeft();

		if (top != null && top.isVisible()) {
			childSize = top.getPreferredSize(areaSize.width, areaSize.height);
			rect.setSize(childSize);
			rect.x = (area.width - childSize.width) / 2 + origin.x;
			rect.y = vMargin + origin.y;
			top.setBounds(rect);
		}
		if (bottom != null && bottom.isVisible()) {
			childSize = bottom.getPreferredSize(areaSize.width, areaSize.height);
			rect.setSize(childSize);
			rect.x = (area.width - childSize.width) / 2 + origin.x;
			rect.y = area.height - childSize.height - vMargin + origin.y;
			bottom.setBounds(rect);
		}
		if (left != null && left.isVisible()) {
			childSize = left.getPreferredSize(areaSize.width, areaSize.height);
			rect.setSize(childSize);
			rect.x = hMargin + origin.x;
			rect.y = (area.height - childSize.height) / 2 + origin.y;
			left.setBounds(rect);
		}
		if (right != null && right.isVisible()) {
			childSize = right.getPreferredSize(areaSize.width, areaSize.height);
			rect.setSize(childSize);
			rect.x = area.width - childSize.width - hMargin + origin.x;
			rect.y = (area.height - childSize.height) / 2 + origin.y;
			right.setBounds(rect);
		}
		if (center != null && center.isVisible()) {
			childSize = center.getPreferredSize(areaSize.width, areaSize.height);
			rect.setSize(childSize);
			rect.x = (area.width - childSize.width) / 2 + origin.x;
			rect.y = (area.height - childSize.height) / 2 + origin.y;
			center.setBounds(rect);
		}
		if (topLeft != null && topLeft.isVisible()) {
			childSize = topLeft.getPreferredSize(areaSize.width, areaSize.height);
			rect.setSize(childSize);
			rect.x = hMargin + origin.x;
			rect.y = vMargin + origin.y;
			topLeft.setBounds(rect);
		}
		if (topRight != null && topRight.isVisible()) {
			childSize = topRight.getPreferredSize(areaSize.width, areaSize.height);
			rect.setSize(childSize);
			rect.x = area.width - childSize.width - hMargin + origin.x;
			rect.y = vMargin + origin.y;
			topRight.setBounds(rect);
		}
		if (bottomLeft != null && bottomLeft.isVisible()) {
			childSize = bottomLeft.getPreferredSize(areaSize.width, areaSize.height);
			rect.setSize(childSize);
			rect.x = hMargin + origin.x;
			rect.y = area.height - childSize.height - vMargin + origin.y;
			bottomLeft.setBounds(rect);
		}
		if (bottomRight != null && bottomRight.isVisible()) {
			childSize = bottomRight.getPreferredSize(areaSize.width, areaSize.height);
			rect.setSize(childSize);
			rect.x = area.width - childSize.width - hMargin + origin.x;
			rect.y = area.height - childSize.height - vMargin + origin.y;
			bottomRight.setBounds(rect);
		}
		if (bottomCenter != null && bottomCenter.isVisible()) {
			childSize = bottomCenter.getPreferredSize(areaSize.width, areaSize.height);
			rect.setSize(childSize);
			rect.x = (area.width - childSize.width - 2 * hMargin) / 2 + origin.x;
			rect.y = area.height - childSize.height - vMargin + origin.y;
			bottomCenter.setBounds(rect);
		}
	}

	@Override
	protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
		return new Dimension(-1, -1);
	}

	/**
	 * Sets the location of hte given child in this layout. Valid constraints:
	 * <UL>
	 * <LI>{@link #CENTER}</LI>
	 * <LI>{@link #TOP}</LI>
	 * <LI>{@link #BOTTOM}</LI>
	 * <LI>{@link #LEFT}</LI>
	 * <LI>{@link #RIGHT}</LI>
	 * <LI><code>null</code> (to remove a child's constraint)</LI>
	 * </UL>
	 * 
	 * <p>
	 * Ensure that the given Figure is indeed a child of the Figure on which
	 * this layout has been set. Proper behaviour cannot be guaranteed if that
	 * is not the case. Also ensure that every child has a valid constraint.
	 * </p>
	 * <p>
	 * Passing a <code>null</code> constraint will invoke
	 * {@link #remove(IFigure)}.
	 * </p>
	 * <p>
	 * If the given child was assigned another constraint earlier, it will be
	 * re-assigned to the new constraint. If there is another child with the
	 * given constraint, it will be over-ridden so that the given child now has
	 * that constraint.
	 * </p>
	 * 
	 * @see org.eclipse.draw2d.AbstractLayout#setConstraint(IFigure, Object)
	 */
	@Override
	public void setConstraint(IFigure child, Object constraint) {
		remove(child);
		super.setConstraint(child, constraint);
		if (constraint == null) {
			return;
		}

		switch (((Integer) constraint).intValue()) {
		case PositionConstants.CENTER:
			center = child;
			break;
		case PositionConstants.TOP:
			top = child;
			break;
		case PositionConstants.BOTTOM:
			bottom = child;
			break;
		case PositionConstants.RIGHT:
			right = child;
			break;
		case PositionConstants.LEFT:
			left = child;
			break;
		case PositionConstants.TOP | PositionConstants.LEFT:
			topLeft = child;
			break;
		case PositionConstants.TOP | PositionConstants.RIGHT:
			topRight = child;
			break;
		case PositionConstants.BOTTOM | PositionConstants.LEFT:
			bottomLeft = child;
			break;
		case PositionConstants.BOTTOM | PositionConstants.RIGHT:
			bottomRight = child;
			break;
		case PositionConstants.BOTTOM | PositionConstants.CENTER:
			bottomCenter = child;
			break;
		default:
			break;
		}
	}

	/**
	 * Sets the horizontal margin to be used on the left/right size. Default is
	 * 0.
	 */
	public void setHorizontalMargin(int margin) {
		hMargin = margin;
	}

	/**
	 * Sets the vertical margin to be used on the left/right size. Default is 0.
	 */
	public void setVerticalMargin(int margin) {
		vMargin = margin;
	}
}
