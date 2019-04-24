package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.VersionStrategy;

public class ChangeVersionStrategyCommandTestStable {

	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();
		controller.getVersionManager().setStrategy(controller.getVersionFactory().createStrategy("Volatile"));
		VersionStrategy v1 = controller.getVersionManager().getStrategy();
		Command command = controller.getCommandFactory().createCommands("ChangeVersionsStrategy", controller);
		controller.setGuiAction("ChangeVersionsStrategy Stable");
		command.execute();
		VersionStrategy v2 = controller.getVersionManager().getStrategy();
		assertNotEquals(v1, v2);
	}

}
