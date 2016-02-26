package wbhgef.editpolicy;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import wbhgef.command.RenameCommand;

/**
 * 策略是根据请求创建相应的命令(Command)
 * 
 * @author software
 *
 */
public class AppRenamePolicy extends AbstractEditPolicy {

	@Override
	public Command getCommand(Request request) {

		if (request.getType().equals("rename")) {
			return createRenameCommand(request);
		}
		return null;
	}

	protected Command createRenameCommand(Request renameRequest) {
		RenameCommand cmd = new RenameCommand();
		cmd.setModel(getHost().getModel());
		cmd.setNewName(((String) renameRequest.getExtendedData().get("newName")));
		return cmd;
	}
}
