package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;

public class CreateCommandTestOther {

	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("Create Other");
		Command command = controller.getCommandFactory().createCommands("Create", controller);
		command.execute();
		assertEquals(controller.getCurrentDocument().getContents(), "");
	}

}
