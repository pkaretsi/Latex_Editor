package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class EditCommandTestContent {

	@Test
	public void test() {
		String articleContents = "\\documentclass[11pt,twocolumn,a4paper]{article}\n\n\\begin{document}"
				+ "\n\\title{Article: How to Structure a LaTeX Document}\n\\author{Author1 \\and Author2 \\and ...}"
				+ "\n\\date{\\today}\n\n\\maketitle\n\n\\section{Section Title 1}\n\n\\section{Section Title 2}"
				+ "\n\n\\section{Section Title.....}\n\n\\section{Conclusion}\n\n\\section*{References}"
				+ "\n\n\\end{document}\n";
		String text = articleContents + " title here";
		LatexEditorController controller = new LatexEditorController();
		controller.setGuiAction("Edit " + text);
		Document newDoc = new Document("me", "today", "2", "unknown", articleContents, "article");
		controller.setCurrentDocument(newDoc);
		Command command = controller.getCommandFactory().createCommands("Edit", controller);
		command.execute();
		assertEquals(controller.getCurrentDocument().getContents(), text);
	}

}
