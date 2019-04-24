package editor.model;

public class VersionManager {
	private VersionStrategy strategy;
	private boolean enabled;
	private Document currentVersion;
	
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
		this.currentVersion = currentVersion;
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
		strategy.removeVersion();
	}

	
}
