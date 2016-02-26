package wbhgef.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import wbhgef.model.Employee;
import wbhgef.model.Node;
import wbhgef.model.Service;

public class PasteNodeCommand extends Command {

	private HashMap<Node, Node> list = new HashMap<Node, Node>();// ，源对象作为key，从key中复制形成的作为value，其主要作为源model和复制的model的对应关系

	@Override
	public boolean canExecute() {
		ArrayList<Node> bList = (ArrayList<Node>) Clipboard.getDefault()
				.getContents();
		if (bList == null || bList.isEmpty()) {
			return false;
		}
		Iterator<Node> it = bList.iterator();
		while (it.hasNext()) {
			Node node = it.next();
			if (isPastableNode(node))
				list.put(node, null);
		}
		return true;
	}

	public void execute() {
		if (!canExecute())
			return;
		Iterator<Node> it = list.keySet().iterator();
		while (it.hasNext()) {
			Node node = it.next();
			try {
				if (node instanceof Service) {
					Service srv = (Service) node;
					Service clone = (Service) srv.clone();
					list.put(node, clone);

				} else if (node instanceof Employee) {
					Employee emp = (Employee) node;
					Employee clone = (Employee) emp.clone();
					list.put(node, clone);
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		redo(); // 这里主要进行复制将复制的元素添加到其父模型中
	}

	@Override
	public void redo() {
		Iterator<Node> it = list.values().iterator();
		while (it.hasNext()) {
			Node node = it.next();
			if (isPastableNode(node))
				node.getParent().addChild(node);
		}
	}

	public boolean canUndo() {
		return list.isEmpty();
	}

	private boolean isPastableNode(Node node) {
		if (node instanceof Service || node instanceof Employee)
			return true;
		return false;
	}
}
