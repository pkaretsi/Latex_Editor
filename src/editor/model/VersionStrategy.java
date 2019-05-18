package editor.model;
import java.util.ArrayList;

//Template Pattern
public abstract class VersionStrategy {
	ArrayList<Document> entireHistory= new ArrayList <Document>();
	
	public Document getVersion() {
		return entireHistory.get(entireHistory.size()-1);
	}
	
	public ArrayList<Document> getEntireHistory() {
		return entireHistory;
	}
	
	public void removeVersion() {
		entireHistory.remove(entireHistory.size()-1);
	}
	
	public void initializeLoadedHistory(ArrayList<Document> history){
		entireHistory.clear();
		entireHistory = history;
	}
	
	public abstract void setEntireHistory(ArrayList<Document> history);

	public abstract void putVersion(Document document);
	
	
	


}
