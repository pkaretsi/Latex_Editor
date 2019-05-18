package editor.controller;

public class CancelRollbackCommand implements Command {

	private LatexEditorController controller;
	
	public CancelRollbackCommand(LatexEditorController controller){
		this.controller = controller;
	}
	
	@Override
	public void execute() {
		if(controller.getVersionManager().getRollbackToNext() == null){
			controller.setStringReturned("Cancellation is not available.");
			return;
		}
		controller.getVersionManager().rollbackToNextVersion();
		controller.setCurrentDocument(controller.getVersionManager().getCurrentVersion());
		//System.out.println("@@@\n" + controller.getCurrentDocument().getContents());
		controller.setStringReturned(controller.getCurrentDocument().getContents());
	}

}
