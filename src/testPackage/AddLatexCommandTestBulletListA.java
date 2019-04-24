package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class AddLatexCommandTestBulletListA {

	@Test
	public void test() {
		String latexCommand ="\\begin{itemize}\n\\item ...\n"+"\\item ...\n\\end{itemize}";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("AddCommand BulletList");
		Document newDoc = new Document("me", "today", "2", "unknown", "", "article");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("AddCommand", controller);
		command.execute();
		assertEquals(controller.getStringReturned(), latexCommand);
	}

}
