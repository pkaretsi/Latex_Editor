package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class AddLatexCommandTestEnumerationListO {

	@Test
	public void test() {
		String latexCommand ="\\begin{enumerate}\n" + "\\item ...\n\\item ...\n\\end{enumerate}";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("AddCommand EnumerationList");
		Document newDoc = new Document("me", "today", "2", "unknown", "", "other");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("AddCommand", controller);
		command.execute();
		assertEquals(controller.getStringReturned(), latexCommand);
	}

}
