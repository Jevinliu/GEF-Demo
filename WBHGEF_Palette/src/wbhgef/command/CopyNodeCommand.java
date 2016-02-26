package wbhgef.command;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import wbhgef.model.Employee;
import wbhgef.model.Node;
import wbhgef.model.Service;

public class CopyNodeCommand extends Command {
	private ArrayList<Node> list = new ArrayList<Node>(); // 所包含的复制节点

	public boolean addElement(Node node) {
		if (!list.contains(node))
			return list.add(node);
		return false;
	}

	@Override
	public boolean canExecute() {

		if (list == null || list.isEmpty()) {
			return false;
		}
		Iterator<Node> it = list.iterator();
		while (it.hasNext()) {
			if (!isCopyableNode(it.next()))
				return false;
		}
		return true;
	}

	public boolean isCopyableNode(Node node) {
		if (node instanceof Service || node instanceof Employee)
			return true;
		return false;
	}

	@Override
	public void execute() {
		if (canExecute())
			Clipboard.getDefault().setContents(list);

	}
}
