package editor.controller;

public class CommandFactory {

	public Command createCommands(String commandID, LatexEditorController controller){
		if(commandID.equalsIgnoreCase("Create")){
			return new CreateCommand(controller);
		}
		else if(commandID.equalsIgnoreCase("Edit")){
			return new EditCommand(controller);
		}
		else if(commandID.equalsIgnoreCase("AddCommand")){
			return new AddLatexCommand(controller);
		}
		else if(commandID.equalsIgnoreCase("EnableVersionsManagement")){
			return new EnableVersionManagementCommand(controller);
		}
		else if(commandID.equalsIgnoreCase("ChangeVersionsStrategy")){
			return new ChangeVersionStrategyCommand(controller);
		}
		else if(commandID.equalsIgnoreCase("DisableVersionsManagement")){
			return new DisableVersionManagementCommand(controller);
		}
		else if(commandID.equalsIgnoreCase("Rollback")){
			return new RollbackToPreviousVersionCommand(controller);
		}
		else if(commandID.equalsIgnoreCase("Save")){
			return new SaveCommand(controller);
		}
		else if(commandID.equalsIgnoreCase("Load")){
			return new LoadCommand(controller);
		}
		else if(commandID.equalsIgnoreCase("CancelRollback")){
			return new CancelRollbackCommand(controller);
		}
		return null;
	}
}
