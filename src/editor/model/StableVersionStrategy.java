package editor.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class StableVersionStrategy implements VersionStrategy {
	
	ArrayList<Document> entireHistory= new ArrayList <Document>();
		
	public void putVersion(Document document) {
		entireHistory.add(document);
		String version = document.getVersionID();
		String date = document.getDate();
		date = date.replaceAll("/", "");
		String filename = "document"+ version + "-" + date + ".txt";
		try{
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(document);
			oos.close();
			file.close();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public Document getVersion() {
		return entireHistory.get(entireHistory.size()-1);
	}
	
	public ArrayList<Document> getEntireHistory() {
		return entireHistory;
	}

	public void setEntireHistory(ArrayList<Document> eh) {
		String filename; 
		FileOutputStream file; 
		ObjectOutputStream oos;
		entireHistory.clear();
		for(Document d : eh){
			String date = d.getDate();
			date = date.replaceAll("/", "");
			filename = "document"+ d.getVersionID() + "-" + date + ".txt";
			try{
				file = new FileOutputStream(filename);
				oos = new ObjectOutputStream(file);
				oos.writeObject(d);
				oos.close();
				file.close();
				entireHistory.add(d);
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		System.out.println("Size of stable history: " + entireHistory.size());
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
	
	public void initializeLoadedHistory(ArrayList<Document> history){
		entireHistory.clear();
		entireHistory = history;
	}
}
