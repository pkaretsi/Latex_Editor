package window.view;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextAreaListener implements DocumentListener {

	private String textChanged;
	private EditorView window;
	
	public TextAreaListener(EditorView w){
		window = w;
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		//System.out.println("Hi");
		textChanged = window.getTextArea().getText();
		String action = "Edit " + textChanged;
		window.getController().enact(action);

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		//System.out.println("You");
		textChanged = window.getTextArea().getText();
		String action = "Edit " + textChanged;
		window.getController().enact(action);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		//System.out.println("There");
		textChanged = window.getTextArea().getText();
		String action = "Edit " + textChanged;
		window.getController().enact(action);
	}

}
