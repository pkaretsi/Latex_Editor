package window.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class CommandListener implements ActionListener {

	private EditorView window;
	private String command;
	
	public CommandListener(EditorView w){
		window = w;
		command = "AddCommand";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String toController = command + " " + e.getActionCommand();
		String addition = window.getController().enact(toController);
		if(window.getController().getCurrentDocument().getDocumentType().equals("letter")){
			JOptionPane.showMessageDialog(window, "You cannot add this LaTeX command in a letter!"); 
			return;
		}
		if(window.getController().getCurrentDocument().getDocumentType().equals("article") 
				&& e.getActionCommand().equals("Chapter")){
			JOptionPane.showMessageDialog(window, "You cannot add a chapter in an article!"); 
			return;
		}
		window.getTextArea().insert(addition, window.getTextArea().getCaretPosition());
		//call enact again to save version after adding a latex command
		toController = "Edit SaveVersion";
		//System.out.println("saveversion after command");
		window.getController().enact(toController);
	}

}
