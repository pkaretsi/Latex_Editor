/*First implementation of Change Version Strategy.
 * In order to test if both version strategies
 * work fine when activated, this command type 
 * should only change versionStrategy in VersionManager
 * for version 1.0 release. Later, the history 
 * will be added.
 */

package editor.controller;

import editor.model.VersionStrategy;

public class ChangeVersionStrategyCommand implements Command {
	
	private LatexEditorController controller;

	public ChangeVersionStrategyCommand(LatexEditorController controller){
		this.controller = controller;
	}

	@Override
	public void execute() {
		String action = controller.getGuiAction();
		String tokens[] = action.split(" ");
		VersionStrategy vs = controller.getVersionFactory().createStrategy(tokens[1]);
		VersionStrategy currentStrategy = controller.getVersionManager().getStrategy();
		vs.setEntireHistory(currentStrategy.getEntireHistory());
		controller.getVersionManager().setStrategy(vs);
	}

}
