package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;

public class CreateCommandTestReport {

	@Test
	public void testExecute() {
		String reportContents = "\\documentclass[11pt,a4paper]{report}\n\n"
				+ "\\begin{document}\n\\title{Report Template: How to Structure a LaTeX Document}"
				+ "\n\\author{Author1 \\and Author2 \\and ...}\n\\date{\\today}\n\\maketitle"
				+ "\n\n\\begin{abstract}\nYour abstract goes here...\n...\n\\end{abstract}"
				+ "\n\n\\chapter{Introduction}\n\\section{Section Title 1}\n\\section{Section Title 2}"
				+ "\n\\section{Section Title.....}\n\n\\chapter{....}\n\n\\chapter{Conclusion}\n\n\n\\chapter*{References}"
				+ "\n\n\\end{document}\n";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("Create Report");
		Command command = controller.getCommandFactory().createCommands("Create", controller);
		command.execute();
		//Document newDoc = controller.getDocumentManager().createDocument("Report");
		assertEquals(controller.getCurrentDocument().getContents(), reportContents);
	}

}
