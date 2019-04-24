package editor.model;

import java.util.ArrayList;

public interface VersionStrategy {
	//ArrayList<Document> entireHistory= new ArrayList <Document>(); 
	//not sure if should be declared here
	
	public void putVersion(Document document);
	
	public Document getVersion();
	
	public void setEntireHistory(ArrayList<Document> history);

	public ArrayList<Document> getEntireHistory();
	
	public void removeVersion();
}
