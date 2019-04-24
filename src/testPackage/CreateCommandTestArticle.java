package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;

public class CreateCommandTestArticle {

	@Test
	public void testExecute() {
		String articleContents = "\\documentclass[11pt,twocolumn,a4paper]{article}\n\n\\begin{document}"
				+ "\n\\title{Article: How to Structure a LaTeX Document}\n\\author{Author1 \\and Author2 \\and ...}"
				+ "\n\\date{\\today}\n\n\\maketitle\n\n\\section{Section Title 1}\n\n\\section{Section Title 2}"
				+ "\n\n\\section{Section Title.....}\n\n\\section{Conclusion}\n\n\\section*{References}"
				+ "\n\n\\end{document}\n";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("Create Article");
		Command command = controller.getCommandFactory().createCommands("Create", controller);
		command.execute();
		assertEquals(controller.getCurrentDocument().getContents(), articleContents);
	}

}
