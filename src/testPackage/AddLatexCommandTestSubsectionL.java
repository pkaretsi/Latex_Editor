package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class AddLatexCommandTestSubsectionL {

	@Test
	public void test() {
		String latexCommand ="\\subsection{...}";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("AddCommand Subsection");
		Document newDoc = new Document("me", "unknown", "2", "", "letter");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("AddCommand", controller);
		command.execute();
		assertNotEquals(controller.getStringReturned(), latexCommand);
	}

}
