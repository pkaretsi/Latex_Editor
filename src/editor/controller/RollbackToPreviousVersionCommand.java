package editor.controller;

public class RollbackToPreviousVersionCommand implements Command {
	
	private LatexEditorController controller;

	public RollbackToPreviousVersionCommand(LatexEditorController controller){
		this.controller = controller;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
