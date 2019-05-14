package editor.model;

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class DocumentManager {
	private HashMap <String, Document> templates = new HashMap <String, Document>();
	//private Document currentDocument = null;
	
	public DocumentManager() {
		loadPrototypes("prototypes.txt");
	}
	
	public void loadPrototypes(String prototypeSpecsFileName) {
		try {
			BufferedReader prototypeSpecsReader = new BufferedReader(
					new FileReader (prototypeSpecsFileName)
					);
			
			String currrentSpec;
			while ((currrentSpec = prototypeSpecsReader.readLine()) != null) {
				String keyStateConfigPair[] = currrentSpec.split(" ");
				initial_doc(keyStateConfigPair[0],keyStateConfigPair[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initial_doc(String docType, String prototypePath) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		//String author = "Unknown";
		String author = System.getProperty("user.name");
		String date = dtf.format(localDate);
		String versionID = "1";
		String copyright = "Property of " + author;
		
		Scanner doc = null;
		String content;
		try{
			
			doc = new Scanner(new FileInputStream(prototypePath));
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		content = "";
		while(doc.hasNextLine()){ 
			content = content + doc.nextLine() + "\n";
		}
		
		Document newDoc = new Document(author,date,copyright,versionID,content,docType);
		templates.put(docType,newDoc);
	}
		
	public Document createDocument(String type) {
		Document copy_doc= new Document(); // default constructor
		switch(type) {
		case "Article":
			return copy_doc.clone(templates.get("article"));
			//currentDocument = copy_doc.clone(templates.get("article"));
			//return currentDocument;
		case "Book":
			return copy_doc.clone(templates.get("book"));
			//currentDocument = copy_doc.clone(templates.get("book"));
			//return currentDocument;
		case "Letter":
			return copy_doc.clone(templates.get("letter"));
			//currentDocument = copy_doc.clone(templates.get("letter"));
			//return currentDocument;
		case "Report":
			return copy_doc.clone(templates.get("report"));
			//currentDocument = copy_doc.clone(templates.get("report"));
			//return currentDocument;
		default:
			return copy_doc.clone(templates.get("other"));
			//currentDocument = copy_doc.clone(templates.get("other"));
			//return currentDocument;	
		}
	}
	
	/*public void setCurrentDocument(Document newd){
		currentDocument = newd;
	}*/

	/*public Document getCurrentDocument() {
		return currentDocument;
	}	*/
}


