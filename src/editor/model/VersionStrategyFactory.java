package editor.model;

public class VersionStrategyFactory {
	
	public VersionStrategy createStrategy(String strategy) {
			if(strategy.equals("Stable")) {
				return new StableVersionStrategy();
			}else if(strategy.equals("Volatile")) {
				return new VolatileVersionStrategy();
			}
			return null;
		
	}
	
}
