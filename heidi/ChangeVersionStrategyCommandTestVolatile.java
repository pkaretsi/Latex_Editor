package testPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;
import editor.model.VersionStrategy;

public class ChangeVersionStrategyCommandTestVolatile {

	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();
		controller.getVersionManager().setStrategy(controller.getVersionFactory().createStrategy("Stable"));
		VersionStrategy v1 = controller.getVersionManager().getStrategy();
		ArrayList<Document> versions=controller.getVersionManager().getStrategy().getEntireHistory();
		int numOfVersions=versions.size();
		Command command = controller.getCommandFactory().createCommands("ChangeVersionsStrategy", controller);
		controller.setGuiAction("ChangeVersionsStrategy Volatile");
		command.execute();
		versions=controller.getVersionManager().getStrategy().getEntireHistory();
		int numOfNewVersions=versions.size();
		VersionStrategy v2 = controller.getVersionManager().getStrategy();
		assertNotEquals(v1, v2);
		assertEquals(numOfVersions,numOfNewVersions);
	}

}
