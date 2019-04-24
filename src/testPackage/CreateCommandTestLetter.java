package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;

public class CreateCommandTestLetter {

	@Test
	public void testExecute() {
		String letterContents = "\\documentclass{letter}\n\\usepackage{hyperref}\n"
				+ "\\signature{Sender's Name}\n\\address{Sender's address...}\n\\begin{document}"
				+ "\n\n\\begin{letter}{Destination address....}\n\\opening{Dear Sir or Madam:}"
				+ "\n\nI am writing to you .......\n\n\n\\closing{Yours Faithfully,}\n\n\\ps\n\nP.S. text ....."
				+ "\n\n\\encl{Copyright permission form}\n\n\\end{letter}\n\\end{document}\n";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("Create Letter");
		Command command = controller.getCommandFactory().createCommands("Create", controller);
		command.execute();
		assertEquals(controller.getCurrentDocument().getContents(), letterContents);
	}

}
