/*Action Listener for Button in control panel.
 * This listener serves button Save, Load, 
 * Create, Rollback and Save Version.
 * */

package window.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ControlActionListener implements ActionListener {

	private EditorView window;
	//String command;
	
	public ControlActionListener(EditorView w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String toController = "";
		String action = e.getActionCommand();
		switch(action){
		case "SaveVersion":
			//command = "Edit";
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
			//command = "Load";
			toController = "Load" + " " + action;
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
		else{
			window.getTextArea().setText(afterExecution);
			toController = "Edit Rollback";
			window.getController().enact(toController);
		}
		//window.getTextArea().selectAll();
		//window.getTextArea().replaceSelection(afterExecution);
		//window.getTextArea().setText(afterExecution);
		//window.getTextArea().insert(afterExecution, window.getTextArea().getCaretPosition());
		//call enact again to save version after adding a latex command
		//toController = "Edit Rollback";
		//System.out.println("saveversion after command");
		//window.getController().enact(toController);
		//window.getTextArea().setText("");
		/*window.getTextArea().setText(afterExecution);
		toController = "Edit Rollback";
		System.out.println("To controller " + toController);
		afterExecution = window.getController().enact(toController);
		window.getTextArea().setText("");
		window.getTextArea().setText(afterExecution);*/
	}

}
