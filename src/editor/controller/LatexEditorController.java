/*LatexEditorController implements the main application
 * logic in the editor. It communicates with GUI and 
 * reacts in different actions via enact method.
 */

package editor.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
			"Save", "Load", "CancelRollback"};
	private String guiAction;
	private String stringReturned;
	private VersionManager versionManager;
	private VersionStrategyFactory versionFactory;
	private String lastContentsSaved = "";
	private Document firstDocument;
	private String fileName="";

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
		System.out.println("GUI " + guiAction);
		if(commands.get(tokens[0])!= null){
			commands.get(tokens[0]).execute();
		}
		return stringReturned;
	}
	
	public void removeHistory(){
		//erases history versions saved in disk storage when
		//having Stable Version Strategy and file is not saved
		ArrayList<Document> docs = getVersionManager().getStrategy().getEntireHistory();
		for(Document d : docs){
			String date = d.getDate();
			date = date.replaceAll("/", "");
			String filename = "document"+ d.getVersionID() + "-" + date + ".txt";
			File f;
			f = new File(filename);
			f.delete();
		}
		docs.clear();
	}
	
	public void handleSavedHistory(){
		String pdir = System.getProperty("user.dir");
		String filepath = pdir + "\\" + fileName + ".txt";
		String historyname=""; 
		ArrayList<Document> h = getVersionManager().getStrategy().getEntireHistory();
		for(Document d : h){
			String date = d.getDate();
			date = date.replaceAll("/", "");
			historyname = historyname + pdir + "\\" + "document"+ d.getVersionID() + "-" + date + ".txt\n";
		}	
		try{
			PrintWriter out = new PrintWriter(filepath);
			out.write(historyname);
			out.flush();
			out.close();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	//Getters and setters
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String filePath) {
		this.fileName = filePath;
	}

	public Document getFirstDocument() {
		return firstDocument;
	}

	public void setFirstDocument(Document firstDocument) {
		this.firstDocument = firstDocument;
	}

	public String getLastContentsSaved() {
		return lastContentsSaved;
	}

	public void setLastContentsSaved(String lastContentsSaved) {
		this.lastContentsSaved = lastContentsSaved;
	}

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
}
