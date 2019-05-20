package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class AddLatexCommandTestSubsubsectionO {

	@Test
	public void test() {
		String latexCommand ="\\subsubsection{...}";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("AddCommand Subsubsection");
		Document newDoc = new Document("me", "unknown", "2", "", "other");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("AddCommand", controller);
		command.execute();
		assertEquals(controller.getStringReturned(), latexCommand);
	}

}
