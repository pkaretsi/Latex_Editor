package testPackage;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class SaveCommandTest {

	//save current document on CURRENT directory
	
	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();
		Document newDoc = new Document("me", "unknown", "2", "\\end{document}", "book");
		controller.setCurrentDocument(newDoc);
		Command editCommand = controller.getCommandFactory().createCommands("Edit", controller);
		String oldContents = controller.getCurrentDocument().getContents();
		String text = oldContents + " Hellooo World";
		controller.setGuiAction("Edit " + text);
		editCommand.execute();
		Command command = controller.getCommandFactory().createCommands("Save", controller);
		controller.setGuiAction("Save");
		command.execute();
		Scanner doc = null;
		String newFileContent="";
		String filename = controller.getFileName()+".tex";
		try{
			doc = new Scanner(new FileInputStream(filename));
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		while(doc.hasNextLine()){ 
			newFileContent=newFileContent+ doc.nextLine()+"\n";
		}
		assertEquals(controller.getCurrentDocument().getContents()+"\n", newFileContent);
	}


}
