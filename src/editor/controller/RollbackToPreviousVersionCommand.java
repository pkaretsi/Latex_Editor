package editor.controller;

public class RollbackToPreviousVersionCommand implements Command {
	
	private LatexEditorController controller;

	public RollbackToPreviousVersionCommand(LatexEditorController controller){
		this.controller = controller;
	}

	@Override
	public void execute() {
		//Supposed to rollback whenever there  is a version stored, although mechanism may not be enabled
		//but you cannot save new versions
		if(controller.getGuiAction().equals("Rollback Cancel")){
			if(controller.getVersionManager().getRollbackToNext() == null){
				controller.setStringReturned("Cancellation is not available");
				return;
			}
			controller.getVersionManager().rollbackToNextVersion();
			controller.setCurrentDocument(controller.getVersionManager().getCurrentVersion());
			controller.setStringReturned(controller.getCurrentDocument().getContents());
			return;
		}
		if(controller.getVersionManager().getStrategy() == null){
			controller.setStringReturned("Mechanism has not been enabled yet");
			return;
		}
		if(controller.getVersionManager().getStrategy().getEntireHistory().size()<=1){
			controller.setStringReturned("Not enough versions to rollback");
			return;
		}
		controller.getVersionManager().rollbackToPreviousVersion();
		controller.setCurrentDocument(controller.getVersionManager().getCurrentVersion());
		controller.setStringReturned(controller.getCurrentDocument().getContents());
	}

}
