/*This test checks if history versions
 * of the initial version strategy and the renewed
 * one are of the same length.
 */

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
		Document newDoc = new Document("me", "unknown", "2", "\\end{document}", "book");
		controller.setCurrentDocument(newDoc);
		//Enable version tracking mechanism
		Command command = controller.getCommandFactory().createCommands("EnableVersionsManagement", controller);
		controller.setGuiAction("EnableVersionsManagement 1");
		command.execute();
		//Set Stable Strategy first
		command = controller.getCommandFactory().createCommands("ChangeVersionsStrategy", controller);
		controller.setGuiAction("ChangeVersionsStrategy Stable");
		command.execute();
		//Save new version
		Command editCommand = controller.getCommandFactory().createCommands("Edit", controller);
		String oldContents = controller.getCurrentDocument().getContents();
		String text = oldContents + " Hellooo World ";
		controller.setGuiAction("Edit " + text);
		editCommand.execute();
		controller.setGuiAction("Edit SaveVersion");
		editCommand.execute();
		VersionStrategy v1 = controller.getVersionManager().getStrategy();
		ArrayList<Document> versions=controller.getVersionManager().getStrategy().getEntireHistory();
		int numOfVersions = versions.size();
		//Change strategy
		command = controller.getCommandFactory().createCommands("ChangeVersionsStrategy", controller);
		controller.setGuiAction("ChangeVersionsStrategy Volatile");
		command.execute();
		versions = controller.getVersionManager().getStrategy().getEntireHistory();
		int numOfNewVersions=versions.size();
		VersionStrategy v2 = controller.getVersionManager().getStrategy();
		
		assertNotEquals(v1, v2);
		assertEquals(numOfVersions, numOfNewVersions);
	}

}
