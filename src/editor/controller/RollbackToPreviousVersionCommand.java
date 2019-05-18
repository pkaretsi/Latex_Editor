package editor.controller;

public class RollbackToPreviousVersionCommand implements Command {
	private LatexEditorController controller;

	public RollbackToPreviousVersionCommand(LatexEditorController controller){
		this.controller = controller;
	}

	@Override
	public void execute() {
		if(controller.getVersionManager().getStrategy() == null){
			controller.setStringReturned("Mechanism has not been enabled yet");
			return;
		}
		if(controller.getVersionManager().getStrategy().getEntireHistory().size()<1){
			controller.setStringReturned("Not enough versions to rollback");
			return;
		}
		if(controller.getVersionManager().getStrategy().getEntireHistory().size()==1){
			controller.getVersionManager().rollbackToPreviousVersion();
			//controller.getVersionManager().getStrategy().removeVersion();
			controller.setCurrentDocument(controller.getFirstDocument().clone(controller.getFirstDocument()));
		}else{
			controller.getVersionManager().rollbackToPreviousVersion();
			controller.setCurrentDocument(controller.getVersionManager().getCurrentVersion());
		}
		controller.setStringReturned(controller.getCurrentDocument().getContents());
	}

}
