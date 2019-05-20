package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class AddLatexCommandTestFigureL {

	@Test
	public void test() {
		String latexCommand ="\\begin{figure}\n\\includegraphics[width=...,height=...]{...}\n"
				+ "\\caption{....}\\label{...}\n\\end{figure}";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("AddCommand Figure");
		Document newDoc = new Document("me", "unknown", "2", "", "letter");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("AddCommand", controller);
		command.execute();
		assertNotEquals(controller.getStringReturned(), latexCommand);
	}

}
