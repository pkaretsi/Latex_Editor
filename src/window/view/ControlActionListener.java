/*Action Listener for Button in control panel.
 * This listener serves button Save, Load, 
 * Create, Rollback and Save Version.
 * */

package window.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import editor.model.StableVersionStrategy;

public class ControlActionListener implements ActionListener {

	private EditorView window;
	
	public ControlActionListener(EditorView w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String toController = "";
		String action = e.getActionCommand();
		switch(action){
		case "SaveVersion":
			toController = "Edit" + " " + action;
			break;
		case "Rollback":
			toController = action;
			break;
		case "Rollback Cancel":
			toController = action;
			break;
		case "Save":
			toController = action;
			break;
		case "Load":
			saveBeforeLoading();
			toController = action;
			break;
		}
		String afterExecution = window.getController().enact(toController);
		if(afterExecution.equals("Mechanism has not been enabled yet")){
			JOptionPane.showMessageDialog(window, "Enable version tracking mechanism first"
					+ " before trying to rollback."); 
		}
		else if(afterExecution.equals("Not enough versions to rollback")){
			JOptionPane.showMessageDialog(window, "There are no versions available to rollback."); 
		}
		else if(afterExecution.equals("Cancellation is not available")){
			JOptionPane.showMessageDialog(window, afterExecution);
		}
		else if(afterExecution.equals("Filename already exists")){
			JOptionPane.showMessageDialog(window, afterExecution + ". Saving is cancelled.");
		}
		else if(afterExecution.equals("File loaded")){
			String newContents = window.getController().getCurrentDocument().getContents();
			window.getTextArea().setText(newContents);
			toController = "Edit " + newContents;
			window.getController().enact(toController);
		}
		else{
			window.getTextArea().setText(afterExecution);
			toController = "Edit Rollback";
			window.getController().enact(toController);
		}
	}
	
	private void saveBeforeLoading(){
		if(!window.getController().getLastContentsSaved().equals(window.getController().getCurrentDocument().getContents())){
            Component rootPane = null;
			int ans = JOptionPane.showConfirmDialog(rootPane, "Save Changes before loading?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
