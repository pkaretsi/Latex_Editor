package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class AddLatexCommandTestTableB {

	@Test
	public void test() {
		String latexCommand ="\\begin{table}\n\\caption{....}\\label{...}\n"
				+ "\\begin{tabular}{|c|c|c|}\n \\hline\n... &...&...\\\\\n... &...&...\\\\\n"
				+ "... &...&...\\\\\n \\hline\n\\end{tabular}\n\\end{table}";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("AddCommand Table");
		Document newDoc = new Document("me", "unknown", "2", "", "book");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("AddCommand", controller);
		command.execute();
		assertEquals(controller.getStringReturned(), latexCommand);
	}

}
