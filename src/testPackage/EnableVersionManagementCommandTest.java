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
		Document newDoc = new Document("me", "unknown", "2", "\\end{document}", "article");
		controller.setCurrentDocument(newDoc);
		String oldContents = controller.getCurrentDocument().getContents();
		
		//Create EnableVersionManagementCommand and execute it
		Command command = controller.getCommandFactory().createCommands("EnableVersionsManagement", controller);
		controller.setGuiAction("EnableVersionsManagement 1");
		command.execute();
		
		//Create EditCommand and execute it
		Command editCommand = controller.getCommandFactory().createCommands("Edit", controller);
		String text = oldContents + " Hellooo";
		controller.setGuiAction("Edit " + text);
		editCommand.execute();
		
		//expect contents to be different
		assertNotEquals(controller.getCurrentDocument().getContents(), oldContents);
	}

}
