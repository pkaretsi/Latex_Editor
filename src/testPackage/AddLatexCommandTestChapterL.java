package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class AddLatexCommandTestChapterL {

	@Test
	public void test() {
		String chapter ="\\chapter{...}";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("AddCommand Chapter");
		Document newDoc = new Document("me", "unknown", "2", "", "letter");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("AddCommand", controller);
		command.execute();
		assertNotEquals("Expect letter to fail", controller.getStringReturned(), chapter);
	}

}
