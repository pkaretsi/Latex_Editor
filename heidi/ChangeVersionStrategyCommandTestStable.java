package testPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;
import editor.model.VersionStrategy;

public class ChangeVersionStrategyCommandTestStable {

	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();
		Document newDoc = new Document("me", "unknown", "2", "\\end{document}", "book");
		controller.setCurrentDocument(newDoc);
		controller.getVersionManager().setStrategy(controller.getVersionFactory().createStrategy("Volatile"));
		Command editCommand = controller.getCommandFactory().createCommands("Edit", controller);
		String oldContents = controller.getCurrentDocument().getContents();
		String text = oldContents + " Hellooo";
		controller.setGuiAction("Edit " + text);
		editCommand.execute();
		controller.setGuiAction("Edit SaveVersion");
		editCommand.execute();
		VersionStrategy v1 = controller.getVersionManager().getStrategy();
		ArrayList<Document> versions=controller.getVersionManager().getStrategy().getEntireHistory();
		int numOfVersions=versions.size();
		Command command = controller.getCommandFactory().createCommands("ChangeVersionsStrategy", controller);
		controller.setGuiAction("ChangeVersionsStrategy Stable");
		command.execute();
		versions=controller.getVersionManager().getStrategy().getEntireHistory();
		int numOfNewVersions=versions.size();
		VersionStrategy v2 = controller.getVersionManager().getStrategy();
		
		assertNotEquals(v1, v2);
		assertEquals(numOfVersions, numOfNewVersions);
		
	}

}
