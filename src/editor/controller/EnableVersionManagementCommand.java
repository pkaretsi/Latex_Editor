package editor.controller;

import editor.model.VersionStrategy;

public class EnableVersionManagementCommand implements Command {
	private LatexEditorController controller;

	public EnableVersionManagementCommand(LatexEditorController controller){
		this.controller = controller;
	}
	
	@Override
	public void execute() {
		String action = controller.getGuiAction();
		String tokens[] = action.split(" ");
		//e.getStateChange = 1 refers to enabled and e.getStateChange = 2 refers to disabled
		//first enabled --> by default volatile strategy
		if(tokens[1].equals("1")){
			controller.getVersionManager().enable();
			VersionStrategy vs = controller.getVersionFactory().createStrategy("Volatile");
			controller.getVersionManager().setStrategy(vs);
		}
		controller.setStringReturned(controller.getCurrentDocument().getContents());
	}

}
