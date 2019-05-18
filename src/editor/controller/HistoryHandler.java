package editor.controller;

import java.awt.Component;

import javax.swing.JOptionPane;

import editor.model.StableVersionStrategy;
import window.view.EditorView;

public class HistoryHandler {

	private String action;
	private EditorView window;
	
	public HistoryHandler(String action, EditorView window){
		this.action = action;
		this.window = window;
	}
	
	public void saveAndHandleHistory(){
		if(!window.getController().getLastContentsSaved().equals
				(window.getController().getCurrentDocument().getContents())){
            Component rootPane = null;
			int ans = JOptionPane.showConfirmDialog(rootPane, "Save Changes before "
            + action + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (ans == JOptionPane.YES_OPTION) {
            	window.getController().enact("Save");
            }
            else{
            	if(window.getController().getVersionManager().isEnabled() &&
            			window.getController().getVersionManager().getStrategy() 
            			instanceof StableVersionStrategy){
            			window.getController().removeHistory();
            	}
            }
		}
		if(window.getController().getVersionManager().isEnabled() &&
            			window.getController().getVersionManager().getStrategy() 
            			instanceof StableVersionStrategy){
			window.getController().handleSavedHistory();
		}
	}
}
