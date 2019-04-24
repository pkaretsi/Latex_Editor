package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class EditCommandTest {

	//@Test
	/*public void testEditCommand() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testExecute() {
		String text = "helloooooooo";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("Edit " + text);
		Document newDoc = new Document();
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("Edit", controller);
		command.execute();
		assertEquals(controller.getCurrentDocument().getContents(), text);
	}

}
