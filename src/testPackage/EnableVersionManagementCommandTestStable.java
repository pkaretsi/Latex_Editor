package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;
import editor.model.VersionStrategy;

public class EnableVersionManagementCommandTestStable {

	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();
		Document newDoc = new Document("me", "today", "2", "unknown", "\\end{document}", "article");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("EnableVersionsManagement", controller);
		Command editCommand = controller.getCommandFactory().createCommands("Edit", controller);
		controller.setGuiAction("EnableVersionsManagement 1");
		command.execute();
		String oldContents = controller.getCurrentDocument().getContents();
		VersionStrategy vs = controller.getVersionFactory().createStrategy("Stable");
		controller.getVersionManager().setStrategy(vs);
		String text = oldContents + " Hellooo";
		controller.setGuiAction("Edit " + text);
		editCommand.execute();
		assertNotEquals(controller.getCurrentDocument().getContents(), oldContents);
	}

}
