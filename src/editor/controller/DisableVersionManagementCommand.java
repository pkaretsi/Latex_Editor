package editor.controller;


public class DisableVersionManagementCommand implements Command {

	private LatexEditorController controller;

	public DisableVersionManagementCommand(LatexEditorController controller){
		this.controller = controller;
	}
	
	@Override
	public void execute() {
		controller.getVersionManager().disable();
		controller.setStringReturned(controller.getCurrentDocument().getContents());
	}

}
