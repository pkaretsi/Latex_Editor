package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class CancelRollbackCommandTest {

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
		Command editCommand = controller.getCommandFactory().createCommands("Edit", controller);
		String text = oldContents + " Hellooo World";
		controller.setGuiAction("Edit " + text);
		editCommand.execute();
		controller.setGuiAction("Edit SaveVersion");
		editCommand.execute();
		String newContents = controller.getCurrentDocument().getContents();
		Command rollBack =controller.getCommandFactory().createCommands("Rollback", controller);
		controller.setGuiAction("Rollback");
		rollBack.execute();
		Command cancelrollBack = controller.getCommandFactory().createCommands("CancelRollback", controller);
		controller.setGuiAction("Rollback Cancel");
		cancelrollBack.execute();
		controller.setGuiAction("Edit SaveVersion");
		controller.setGuiAction("Edit SaveVersion");
		editCommand.execute();
		assertEquals(controller.getCurrentDocument().getContents(), newContents);
	}

}
