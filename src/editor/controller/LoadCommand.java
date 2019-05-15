/* Should we be aware of document type? If yes, how, because we do not
 * know if we open a template
 * Should we use document manager? If yes, we should modify createDocument there
 * How do we know if there is history tracked in disk storage? - Maybe when saving file, rename history?
 * */

package editor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.HashMap;
import java.util.HashSet;

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
			String type = loadedType(selectedFile);
			//System.out.println(type);
			String tokens[] = fileChooser.getSelectedFile().getName().split(".tex");
			//System.out.println(tokens[0]);
			controller.setFileName(tokens[0]);
			controller.getDocumentManager().setLoaded(true);
			//loadHistory(tokens[0]);
			Document ld = new Document(System.getProperty("user.name"),"","Property of ","1",contents,type);
			controller.setCurrentDocument(ld);
			controller.setStringReturned("File loaded");
		}
	}
	
	private String readFile(File file){
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));  
			String st, content=""; 
			
			while ((st = br.readLine()) != null){
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
	
	
	private String loadedType(File file) {
		try{
			Scanner br = new Scanner(new FileInputStream(file));
			String st,type="other";
			boolean searchType=true;
			st = br.nextLine();
			while(searchType) {
				int offset = st.indexOf("documentclass");
				if(offset!=-1) {
					searchType=false;
					break;
				}
				if(!br.hasNextLine()) {
					break;
				}
				st = br.nextLine();
			}
			if(searchType == false) {
				int offset = st.indexOf('{')+1;
				int end = st.length()-1;
				type = st.substring(offset,end);
				HashMap<String,Document> map = controller.getDocumentManager().getTemplates();
				Set<String> docTypes = map.keySet();
				if(docTypes.contains(type)){
					return type;
				}
				else{
					return "other";
				}
			}
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return "other";	
	}
}
