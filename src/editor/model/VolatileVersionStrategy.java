package editor.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class VolatileVersionStrategy implements VersionStrategy {

	ArrayList<Document> entireHistory = new ArrayList <Document>();
	
	//public VolatileVersionStrategy() {}
		
	public void putVersion(Document document) {
		entireHistory.add(document);
	}
	
	public Document getVersion() {
		return entireHistory.get(entireHistory.size()-1);
	}
	
	public ArrayList<Document> getEntireHistory() {
		return entireHistory;
	}

	public void setEntireHistory(ArrayList<Document> eh) {
		String filename; 
		FileInputStream file;
		ObjectInputStream in;
		Document doc;
		entireHistory.clear(); //sets to empty history, ready for new additions?
		for(Document d : eh){
			String date = d.getDate();
			date = date.replaceAll("/", "");
			filename = "document"+ d.getVersionID() + "-" + date + ".tex";
			try{
				file = new FileInputStream(filename);
				in = new ObjectInputStream(file);
				doc = (Document)in.readObject();
				in.close();
				file.close();
				File f = new File(filename);
				f.delete(); //delete files from disk
				entireHistory.add(doc);
			}
			catch(IOException ex){ 
				ex.printStackTrace();
	        }  
	        catch(ClassNotFoundException ex){
	            System.out.println("ClassNotFoundException is caught"); 
	            ex.printStackTrace();
	        }
		}
		System.out.println("Size of volatile history " + entireHistory.size());
		//For testing only!!!
		/*for (Document document: entireHistory){
			System.out.println("\n");
			System.out.println("author = " + document.getAuthor()); 
            System.out.println("date = " + document.getDate());
            System.out.println("contents = " + document.getContents());
            System.out.println("copyright = " + document.getCopyright());
            System.out.println("document type = " + document.getDocumentType());
            System.out.println("date = " + document.getDate());
            System.out.println("version = " + document.getVersionID());
		}*/
	}
	
	public void removeVersion() {
		entireHistory.remove(entireHistory.size()-1);
	}
	
}