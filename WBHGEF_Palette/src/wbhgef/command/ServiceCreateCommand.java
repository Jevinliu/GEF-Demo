package wbhgef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import wbhgef.model.Enterprise;
import wbhgef.model.Service;

public class ServiceCreateCommand extends Command {
	private Enterprise enterprise;
	private Service service;

	public ServiceCreateCommand() {
		super();
		enterprise = null;
		service = null;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Object en) {
		if (en instanceof Enterprise)
			this.enterprise = (Enterprise) en;
	}

	public Service getService() {
		return service;
	}

	public void setService(Object obj) {
		if (obj instanceof Service)
			this.service = (Service) obj;
	}

	public void setLayout(Rectangle r) {
		if (service == null) {
			return;
		}
		service.setLayout(r);
	}

	@Override
	public boolean canExecute() {
		if (service == null || enterprise == null) {
			return false;
		}
		return true;
	}

	@Override
	public void execute() {
		enterprise.addChild(service);
	}

	@Override
	public boolean canUndo() {
		if (service == null || enterprise == null) {
			return false;
		}
		return enterprise.contains(service);
	}

	@Override
	public void undo() {
		enterprise.removeChild(service);
	}
}
