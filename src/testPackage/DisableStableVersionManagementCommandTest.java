package testPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;
import editor.model.VersionStrategy;

public class DisableStableVersionManagementCommandTest {

	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();
		Document newDoc = new Document("me", "unknown", "2", "\\end{document}", "book");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("EnableVersionsManagement", controller);
		controller.setGuiAction("EnableVersionsManagement 1");
		command.execute();
		VersionStrategy vs = controller.getVersionFactory().createStrategy("Stable");
		controller.getVersionManager().setStrategy(vs);
		Command editCommand = controller.getCommandFactory().createCommands("Edit", controller);
		String oldContents = controller.getCurrentDocument().getContents();
		String text = oldContents + " Hellooo";
		controller.setGuiAction("Edit " + text);
		editCommand.execute();
		controller.setGuiAction("Edit SaveVersion");
		editCommand.execute();
		ArrayList<Document> versions=controller.getVersionManager().getStrategy().getEntireHistory();
		int numOfVersions=versions.size();
		Command cancelcommand = controller.getCommandFactory().createCommands("DisableVersionsManagement", controller);
		controller.setGuiAction("DisableVersionsManagement");
		controller.setGuiAction("EnableVersionsManagement 0");
		cancelcommand.execute();
		oldContents = controller.getCurrentDocument().getContents();
		text = oldContents + " canceled";
		controller.setGuiAction("Edit " + text);
		editCommand.execute();
		controller.setGuiAction("Edit SaveVersion");
		editCommand.execute();
		versions = controller.getVersionManager().getStrategy().getEntireHistory();
		int numOfCurrentVersions=versions.size();
		assertEquals(numOfVersions, numOfCurrentVersions);
		assertNotEquals(controller.getVersionManager().isEnabled(), true);
	}

}
