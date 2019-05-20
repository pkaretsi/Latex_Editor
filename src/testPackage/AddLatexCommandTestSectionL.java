package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class AddLatexCommandTestSectionL {

	@Test
	public void test() {
		String latexCommand ="\\section{...}";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("AddCommand Section");
		Document newDoc = new Document("me", "unknown", "2", "", "letter");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("AddCommand", controller);
		command.execute();
		assertNotEquals(controller.getStringReturned(), latexCommand);
	}

}
