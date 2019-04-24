package testPackage;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class AddLatexCommandTestChapterA {

	@Test
	public void test() {
		String chapter ="\\chapter{...}";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("AddCommand Chapter");
		Document newDoc = new Document("me", "today", "2", "unknown", "", "article");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("AddCommand", controller);
		command.execute();
		assertNotEquals("Article does not pass chapter command", controller.getStringReturned(), chapter);
	}

}
