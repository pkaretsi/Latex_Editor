/* Should we be aware of document type? If yes, how, because we do not
 * know if we open a template
 * Should we use document manager? If yes, we should modify createDocument there
 * How do we know if there is history tracked in disk storage? - Maybe when saving file, rename history?
 * */

package editor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import editor.model.Document;

public class LoadCommand implements Command {

	private LatexEditorController controller;

	public LoadCommand(LatexEditorController controller){
		this.controller = controller;
	}
	
	@Override
	public void execute() {
		JFileChooser fileChooser = new JFileChooser("C:");
		FileNameExtensionFilter texfilter = new FileNameExtensionFilter("LaTeX files", "tex");
		fileChooser.setFileFilter(texfilter);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String contents = readFile(selectedFile);
			Document ld = new Document();
			ld.setContents(contents);
			controller.setCurrentDocument(ld);
			controller.setStringReturned("File loaded");
		}
	}
	
	private String readFile(File file){
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));  
			String st, content=""; 
			while ((st = br.readLine()) != null){
				//System.out.println(st);
				content += st + "\n";
			}
			System.out.println(content);
			return content;
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return "";
	}

}
