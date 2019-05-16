package window.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstMenuItemListener implements ActionListener{
	private EditorView window;
	
	public FirstMenuItemListener(EditorView w){
		this.window = w;
	}
		
	public void actionPerformed(ActionEvent e) {            
		switchPanel(e);
	}    
	
	private void switchPanel(ActionEvent e){
		CardLayout cardLayout = (CardLayout) window.getWindowPanel().getLayout();
		cardLayout.show(window.getWindowPanel(), "Panel after create command");
		String action = e.getActionCommand();
		String textFile = window.getController().enact(action);
		window.getTextArea().setText("");
		window.getTextArea().setText(textFile);
		window.setSwitched(true);
	}
}
