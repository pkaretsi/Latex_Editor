/*Action Listener for Button in control panel.
 * This listener serves button Save, Load, 
 * Create, Rollback and Save Version.
 * */

package window.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlActionListener implements ActionListener {

	private EditorView window;
	String command;
	
	public ControlActionListener(EditorView w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String toController = "";
		String action = e.getActionCommand();
		switch(action){
		case "SaveVersion":
			command = "Edit";
			toController = command + " " + action;
			break;
		case "Load":
			command = "Load";
			toController = command + " " + action;
			break;
		}
		String addition = window.getController().enact(toController);
	}

}
