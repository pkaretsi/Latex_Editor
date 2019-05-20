package testPackage;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;

public class LoadCommandTest {

	/*create a new document on current directory
	 * or open an existing one from CURRENT directory.
	 * */
	
	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();

		Command command = controller.getCommandFactory().createCommands("Load", controller);
		controller.setGuiAction("Load");
		command.execute();
		Scanner doc = null;
		String loadedFileContent="";
		String filename = controller.getFileName()+".tex";
		try{
			doc = new Scanner(new FileInputStream(filename));
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		while(doc.hasNextLine()){ 
			loadedFileContent=loadedFileContent+ doc.nextLine()+"\n";
		}
		assertEquals(controller.getCurrentDocument().getContents(), loadedFileContent);
	}

}
