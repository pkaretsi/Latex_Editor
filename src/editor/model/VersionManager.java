package editor.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class VersionManager {
	private VersionStrategy strategy;
	private boolean enabled;
	private Document currentVersion;
	private Document rollbackToNext;
	private Document firstVersion; //
	
	public Document getFirstVersion() {
		return firstVersion;
	}

	public void setFirstVersion(Document firstVersion) {
		this.firstVersion = firstVersion;
	}

	public VersionManager() {}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void enable() {
		this.enabled=true;
	}
	
	public void disable() {
		this.enabled=false;
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

	public Document getPreviousVersion() {
		return strategy.getVersion();
	}
	
	public void rollbackToPreviousVersion() {
		rollbackToNext = strategy.getVersion(); //version to be deleted
		if(strategy instanceof StableVersionStrategy){
			String filename; 
			String date = rollbackToNext.getDate();
			date = date.replaceAll("/", "");
			filename = "document"+ rollbackToNext.getVersionID() + "-" + date + ".tex";
			File f = new File(filename);
			f.delete(); //delete file from disk
		}
		if(strategy.getEntireHistory().size()==1){
			strategy.removeVersion();
			this.currentVersion = firstVersion.clone(firstVersion); 
			//System.out.println(currentVersion.getContents() + " new heree");
			//System.out.println("\n\n\n" + firstVersion.getContents() + "\n\n\n");
		}
		else{
			strategy.removeVersion();
			Document previousd = strategy.getVersion(); //now the last one is the version we want to recover
			this.currentVersion = previousd.clone(previousd); //clone because otherwise it passes a shallow copy
			//Document previousd = getPreviousVersion(); //gets the last one not the second from the end :(
			//setCurrentVersion(previousd); if setCurrent, 2 duplicates of the same thing in AL
		}
	}

	public Document getRollbackToNext() {
		return rollbackToNext;
	}
	
	public void rollbackToNextVersion(){
		setCurrentVersion(rollbackToNext);
		rollbackToNext = null;
		
	}
	
}
