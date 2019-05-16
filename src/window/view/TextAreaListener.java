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
		textChanged = window.getTextArea().getText();
		String action = "Edit " + textChanged;
		window.getController().enact(action);

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		textChanged = window.getTextArea().getText();
		String action = "Edit " + textChanged;
		window.getController().enact(action);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		textChanged = window.getTextArea().getText();
		String action = "Edit " + textChanged;
		window.getController().enact(action);
	}

}
