package editor.model;

import java.io.File;
import java.util.ArrayList;

public class VersionManager {
	private VersionStrategy strategy;
	private boolean enabled;
	private Document currentVersion;
	private Document rollbackToNext;
	private ArrayList<Document> loadedHistory;
	
	public Document getPreviousVersion() {
		return strategy.getVersion();
	}

	public void rollbackToPreviousVersion() {
		rollbackToNext = strategy.getVersion(); //version to be deleted
		if(strategy instanceof StableVersionStrategy){
			String filename; 
			String date = strategy.getVersion().getDate();
			date = date.replaceAll("/", "");
			filename = "document"+ strategy.getVersion().getVersionID() + "-" + date + ".txt";
			File f = new File(filename);
			f.delete();
		}
		strategy.removeVersion();
		if(strategy.getEntireHistory().size() != 0){
			Document previousd = strategy.getVersion(); //now the last one is the version we want to recover
			this.currentVersion = previousd.clone(previousd); //clone because otherwise it passes a shallow copy
		}
	}

	public Document getRollbackToNext() {
		return rollbackToNext;
	}
	
	public void rollbackToNextVersion(){
		setCurrentVersion(rollbackToNext);
		rollbackToNext = null;
	}
	
	//Setters and Getters
	public ArrayList<Document> getLoadedHistory() {
		return loadedHistory;
	}

	public void setLoadedHistory(ArrayList<Document> loadedHistory) {
		this.loadedHistory = loadedHistory;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public Document getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Document currentVersion) {
		strategy.putVersion(currentVersion);
		this.currentVersion = currentVersion.clone(currentVersion);
	}

	public VersionStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(VersionStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void enable() {
		this.enabled=true;
	}
	
	public void disable() {
		this.enabled=false;
	}
	
}
