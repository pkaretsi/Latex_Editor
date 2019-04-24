package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class EnableVersionManagementCommandTest {

	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();
		Document newDoc = new Document("me", "today", "2", "unknown", "\\end{document}", "article");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("EnableVersionsManagement", controller);
		Command editCommand = controller.getCommandFactory().createCommands("Edit", controller);
		String oldContents = controller.getCurrentDocument().getContents();
		String text = oldContents + " Hellooo";
		controller.setGuiAction("Edit " + text);
		editCommand.execute();
		controller.setGuiAction("EnableVersionsManagement 1");
		command.execute();
		//expect contents to be different
		assertNotEquals(controller.getCurrentDocument().getContents(), oldContents);
	}

}
