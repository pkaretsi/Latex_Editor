package editor.controller;

import editor.model.VersionStrategy;


public class EnableVersionManagementCommand implements Command {
	private LatexEditorController controller;

	public EnableVersionManagementCommand(LatexEditorController controller){
		this.controller = controller;
	}
	
	@Override
	public void execute() {
		controller.getVersionManager().enable();
		VersionStrategy vs = controller.getVersionFactory().createStrategy("Volatile");
		if(controller.getVersionManager().getLoadedHistory() != null){
			vs.initializeLoadedHistory(controller.getVersionManager().getLoadedHistory());
			controller.getVersionManager().setLoadedHistory(null);
		}
		VersionStrategy previousStrategy = controller.getVersionManager().getStrategy();
		if (previousStrategy != null){
			vs.getEntireHistory().addAll(previousStrategy.getEntireHistory());
		}
		controller.getVersionManager().setStrategy(vs);
		controller.setStringReturned(controller.getCurrentDocument().getContents());
	}
	
}
