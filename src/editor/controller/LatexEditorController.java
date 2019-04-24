/*LatexEditorController implements the main application
 * logic in the editor. It communicates with GUI and 
 * reacts in different actions via enact method.
 */

package editor.controller;

import java.util.HashMap;

import editor.model.Document;
import editor.model.DocumentManager;
import editor.model.VersionManager;
import editor.model.VersionStrategyFactory;

public class LatexEditorController {
	private CommandFactory commandFactory;
	private HashMap<String, Command> commands;
	private DocumentManager documentManager;
	private Document currentDocument = null;
	private String commandTypes[] = {"Create", "Edit", "AddCommand", "EnableVersionsManagement",
			"ChangeVersionsStrategy", "DisableVersionsManagement", "Rollback", 
			"Save", "Load"};
	private String guiAction;
	private String stringReturned;
	private VersionManager versionManager;
	private VersionStrategyFactory versionFactory;

	
	public String getStringReturned() {
		return stringReturned;
	}

	public CommandFactory getCommandFactory() {
		return commandFactory;
	}

	public VersionStrategyFactory getVersionFactory() {
		return versionFactory;
	}

	public String getGuiAction() {
		return guiAction;
	}

	public void setGuiAction(String guiAction) {
		this.guiAction = guiAction;
	}

	public Document getCurrentDocument() {
		return currentDocument;
	}

	public void setCurrentDocument(Document currentDocument) {
		this.currentDocument = currentDocument;
	}

	public DocumentManager getDocumentManager() {
		return documentManager;
	}

	public void setStringReturned(String stringReturned) {
		this.stringReturned = stringReturned;
	}
	
	public VersionManager getVersionManager() {
		return versionManager;
	}

	public LatexEditorController(){
		documentManager = new DocumentManager();
		commandFactory = new CommandFactory();
		commands = new HashMap<String, Command>();
		versionManager = new VersionManager();
		versionFactory = new VersionStrategyFactory();
		initializeHashMap();
	}
	
	private void initializeHashMap(){
		for(int i=0; i<commandTypes.length; i++){
			commands.put(commandTypes[i], commandFactory.createCommands(commandTypes[i], this));
		}
	}
	
	public String enact(String command){
		guiAction = command;
		String tokens[] = command.split(" ");
		System.out.println(tokens[0]);
		if(commands.get(tokens[0])!= null){
			commands.get(tokens[0]).execute();
		}
		//System.out.println("@@@\n"+stringReturned);
		return stringReturned;
	}
}
