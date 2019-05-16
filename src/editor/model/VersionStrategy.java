package editor.model;

import java.util.ArrayList;

public interface VersionStrategy {
	
	public void putVersion(Document document);
	
	public Document getVersion();
	
	public void setEntireHistory(ArrayList<Document> history);

	public ArrayList<Document> getEntireHistory();
	
	public void removeVersion();
	
	public void initializeLoadedHistory(ArrayList<Document> history);
}
