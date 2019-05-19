package testPackage;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class LoadCommandTest {

	//create a document named "new_doc" on current directory
	
	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();

		Command command = controller.getCommandFactory().createCommands("Load", controller);
		controller.setGuiAction("Load");
		command.execute();
		Scanner doc = null;
		String loadedFileContent="";
		try{
			doc = new Scanner(new FileInputStream("new_doc.tex"));
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		while(doc.hasNextLine()){ 
			loadedFileContent=loadedFileContent+ doc.nextLine()+"\n";
		}
		assertEquals(controller.getCurrentDocument().getContents(), loadedFileContent);
	}

}
