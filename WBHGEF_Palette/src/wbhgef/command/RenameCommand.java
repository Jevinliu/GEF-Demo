package wbhgef.command;

import org.eclipse.gef.commands.Command;

import wbhgef.model.Node;

public class RenameCommand extends Command {

	private Node model;
	private String oldName;
	private String newName;

	public void setModel(Object model) {
		this.model = (Node) model;
	}

	public void execute() {
		this.oldName = model.getName();
		this.model.setName(newName);
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public void undo() {
		this.model.setName(oldName);
	}
}
