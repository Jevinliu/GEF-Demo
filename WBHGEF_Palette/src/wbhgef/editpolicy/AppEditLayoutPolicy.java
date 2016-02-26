package wbhgef.editpolicy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import wbhgef.command.AbstractLayoutCommand;
import wbhgef.command.EmployeeChangeLayoutCommand;
import wbhgef.command.EmployeeCreateCommand;
import wbhgef.command.ServiceChangeLayoutCommand;
import wbhgef.command.ServiceCreateCommand;
import wbhgef.editpart.EmployeePart;
import wbhgef.editpart.EnterprisePart;
import wbhgef.editpart.ServicePart;
import wbhgef.figure.EmployeeFigure;
import wbhgef.figure.ServiceFigure;

public class AppEditLayoutPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command createChangeConstraintCommand(
			ChangeBoundsRequest request, EditPart child, Object constraint) {
		AbstractLayoutCommand command = null;
		if (child instanceof EmployeePart) {
			command = new EmployeeChangeLayoutCommand();
		} else if (child instanceof ServicePart) {
			command = new ServiceChangeLayoutCommand();
		}
		if (command != null) {
			command.setModel(child.getModel());
			command.setConstraint((Rectangle) constraint);
		}

		return command;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {

		if (request.getType() == REQ_CREATE
				&& getHost() instanceof EnterprisePart) {
			ServiceCreateCommand cmd = new ServiceCreateCommand();
			cmd.setEnterprise(getHost().getModel()); // 宿主对象
			cmd.setService(request.getNewObject());

			Rectangle constraint = (Rectangle) getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0) ? ServiceFigure.SERVICE_FIGURE_DEFWIDTH
					: constraint.width;
			constraint.height = (constraint.height <= 0) ? ServiceFigure.SERVICE_FIGURE_DEFHEIGHT
					: constraint.height;
			cmd.setLayout(constraint);
			return cmd;
		} else if (request.getType() == REQ_CREATE
				&& getHost() instanceof ServicePart) {
			EmployeeCreateCommand cmd = new EmployeeCreateCommand();
			cmd.setService(getHost().getModel());
			cmd.setEmployee(request.getNewObject());
			Rectangle constraint = (Rectangle) getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0) ? EmployeeFigure.EMPLOYE_FIGURE_DEFWIDTH
					: constraint.width;
			constraint.height = (constraint.height <= 0) ? EmployeeFigure.EMPLOYE_FIGURE_DEFHEIGHT
					: constraint.height;
			cmd.setLayout(constraint);
			return cmd;
		}
		return null;
	}

}
