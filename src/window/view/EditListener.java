package window.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* Implements Action Listener for edit commands from right column */

public class EditListener implements ActionListener {

	private EditorView window;
	
	public EditListener(EditorView w){
		window = w;
	}
	
	public void actionPerformed(ActionEvent e) {  
		String action = e.getActionCommand();
		System.out.println(action);
		if(action.equalsIgnoreCase("cut")){
			window.getTextArea().cut();
		}
		else if(action.equalsIgnoreCase("copy")){
			window.getTextArea().copy();
		}
		else if(action.equalsIgnoreCase("paste")){
			window.getTextArea().paste();
		}
		else if(action.equalsIgnoreCase("selectAll")){
			window.getTextArea().selectAll();
		}
    }    
}
