package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class RollbackCommandTest2 {

	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();
		Document newDoc = new Document("me", "unknown", "2", "\\end{document}", "book");
		controller.setCurrentDocument(newDoc);
		controller.setFirstDocument(newDoc.clone(newDoc));
		String oldContents = controller.getCurrentDocument().getContents();
		Command command = controller.getCommandFactory().createCommands("EnableVersionsManagement", controller);
		controller.setGuiAction("EnableVersionsManagement 1");
		command.execute();
		//Version 1
		Command editCommand = controller.getCommandFactory().createCommands("Edit", controller);
		String text = oldContents + " Hellooo World";
		controller.setGuiAction("Edit " + text);
		editCommand.execute();
		controller.setGuiAction("Edit SaveVersion");
		editCommand.execute();
		//Version 2
		text = oldContents + " Hellooo World 2nd version";
		controller.setGuiAction("Edit " + text);
		editCommand.execute();
		controller.setGuiAction("Edit SaveVersion");
		editCommand.execute();
		Command rollBack = controller.getCommandFactory().createCommands("Rollback", controller);
		//Rollback 1
		controller.setGuiAction("Rollback");
		rollBack.execute();
		assertNotEquals(controller.getCurrentDocument().getContents(), oldContents);
		//Rollback 2
		controller.setGuiAction("Rollback");
		rollBack.execute();
		assertEquals(controller.getCurrentDocument().getContents(), oldContents);
	}


}
