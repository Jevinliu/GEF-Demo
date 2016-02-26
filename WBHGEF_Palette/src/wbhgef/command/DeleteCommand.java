package wbhgef.command;

import org.eclipse.gef.commands.Command;

import wbhgef.model.Node;

public class DeleteCommand extends Command {

	private Node model;
	private Node parentModel;

	public void execute() {
		this.parentModel.removeChild(model);
	}

	public void setModel(Object model) {
		this.model = (Node) model;
	}

	public void setParentModel(Object model) {
		parentModel = (Node) model;
	}

	// 命令站在 editDomin中自动生成，同时，redo操作相当于undo的反向，有框架取代，所以只需要实现undo操作
	public void undo() {
		this.parentModel.addChild(model);
	}
}
