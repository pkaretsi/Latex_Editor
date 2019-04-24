package editor.controller;

import editor.model.VersionStrategy;

public class DisableVersionManagementCommand implements Command {

	private LatexEditorController controller;

	public DisableVersionManagementCommand(LatexEditorController controller){
		this.controller = controller;
	}
	
	@Override
	public void execute() {
		String action = controller.getGuiAction();
		String tokens[] = action.split(" ");
		//e.getStateChange = 1 refers to enabled and e.getStateChange = 2 refers to disabled
		//first enabled --> by default volatile strategy
		if(tokens[1].equals("2")){
			controller.getVersionManager().disable();
			//more to be added
		}
		controller.setStringReturned(controller.getCurrentDocument().getContents());
	}

}
