package wbhgef.command;

import org.eclipse.draw2d.geometry.Rectangle;

import wbhgef.model.Employee;

/**
 * 
 * Employee改变布局的Command类的代码
 * 
 * @author software
 *
 */
public class EmployeeChangeLayoutCommand extends AbstractLayoutCommand {

	private Employee model;
	private Rectangle layout;
	private Rectangle oldLayout;

	@Override
	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	@Override
	public void setModel(Object model) {
		this.model = (Employee) model;
		this.oldLayout = ((Employee) model).getLayout();
	}

	@Override
	public void execute() {
		model.setLayout(layout);
	}

	@Override
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}
}
