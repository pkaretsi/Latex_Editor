package editor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.ArrayList;
import java.util.HashMap;

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
			String tokens[] = fileChooser.getSelectedFile().getName().split(".tex");
			controller.setFileName(tokens[0]);
			String currentVersion = loadVersionHistory();
			Document ld = new Document(System.getProperty("user.name"),"", "Property of ",
					currentVersion, contents, type);
			controller.setFirstDocument(ld.clone(ld));
			controller.setCurrentDocument(ld);
			controller.setStringReturned("File loaded");
		}
		else{
			controller.setStringReturned("Operation cancelled");
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
			br.close();
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
			while(searchType && br.hasNextLine()) {
				st = br.nextLine();
				int offset = st.indexOf("documentclass");
				if(offset!=-1) {
					int offset2 = st.indexOf('{')+1;
					int end = st.length()-1;
					type = st.substring(offset2,end);
					HashMap<String,Document> map = controller.getDocumentManager().getTemplates();
					Set<String> docTypes = map.keySet();
					if(docTypes.contains(type)){
						br.close();
						return type;
					}
				}
			}
			br.close();
			return "other";
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		return "other";	
	}
	
	private String loadVersionHistory(){
		String pdir = System.getProperty("user.dir") + '\\' + controller.getFileName() + ".txt";
		Scanner sc = null;
		String content;
		ArrayList<Document> lhistory = new ArrayList<Document>();
		FileInputStream file;
		ObjectInputStream in;
		Document doc;
		try{
			sc = new Scanner(new FileInputStream(pdir));
			content = "";
			while(sc.hasNextLine()){ 
				content = sc.nextLine();
				File cp = new File(content);
				if(cp.exists() && cp.isFile()){
					try{
						file = new FileInputStream(content);
						in = new ObjectInputStream(file);
						doc = (Document)in.readObject();
						lhistory.add(doc);
						in.close();
						file.close();
					}
					catch(FileNotFoundException e){
						e.printStackTrace();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
			 		catch (ClassNotFoundException e) {
			 			e.printStackTrace();
			 		}
				}
			}
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println("There is no history saved");
		}
		System.out.println("$$$$ " + lhistory.size()); ///
		if(controller.getVersionManager().getStrategy()!=null){
			controller.getVersionManager().getStrategy().initializeLoadedHistory(lhistory);
		}
		else{
			controller.getVersionManager().setLoadedHistory(lhistory);
		}
		return String.valueOf(lhistory.size()+1);
	}
}
