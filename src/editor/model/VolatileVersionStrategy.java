package editor.model;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class VolatileVersionStrategy extends VersionStrategy{

	@Override
	public void setEntireHistory(ArrayList<Document> eh) {
		String filename; 
		FileInputStream file;
		ObjectInputStream in;
		Document doc;
		entireHistory.clear();
		for(Document d : eh){
			String date = d.getDate();
			date = date.replaceAll("/", "");
			filename = "document"+ d.getVersionID() + "-" + date + ".txt";
			try{
				file = new FileInputStream(filename);
				in = new ObjectInputStream(file);
				doc = (Document)in.readObject();
				in.close();
				file.close();
				File f = new File(filename);
				f.delete();
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

	@Override
	public void putVersion(Document document) {
		entireHistory.add(document);
	}

}
