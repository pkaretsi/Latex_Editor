package window.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import editor.controller.LatexEditorController;
import editor.model.Document;
import editor.model.StableVersionStrategy;


public class EditorView extends JFrame {
	
	private JPanel windowPanel = new JPanel(new CardLayout());
	private JTextArea textArea;
	private LatexEditorController controller;
	private boolean switched;
	
	public EditorView(){
		super("Latex Editor");
		controller = new LatexEditorController();
		switched = false;
		initComponents();
		this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(java.awt.event.WindowEvent e) {
	        	if(switched){
	        		if(!controller.getLastContentsSaved().equals(controller.getCurrentDocument().getContents())){
	        			super.windowClosing(e);
		                int ans = JOptionPane.showConfirmDialog(rootPane, "Save Changes ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		                if (ans == JOptionPane.YES_OPTION) {
		                	getController().enact("Save");
		                }
		                else{
		                	if(getController().getVersionManager().isEnabled() &&
		                			getController().getVersionManager().getStrategy() 
		                			instanceof StableVersionStrategy){
		                			getController().removeHistory();
		                			//System.out.println("%%% " + getController().getVersionManager().getStrategy().getEntireHistory().size());
		                	}
		                }
	        		}
	        		if(getController().getVersionManager().isEnabled() &&
		                			getController().getVersionManager().getStrategy() 
		                			instanceof StableVersionStrategy){
	        			getController().handleSavedHistory();
	        		}
	        	}
	        }
	   });
	}
	
	public void setSwitched(boolean switched){
		this.switched = switched;
	}
	
	public JPanel getWindowPanel(){
		return windowPanel;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public LatexEditorController getController(){
		return controller;
	}
	
	private void initComponents(){
		textArea = new JTextArea("",5,20);
		textArea.getDocument().addDocumentListener(new TextAreaListener(this));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(new Color(70, 130, 180));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
	    setSize((int)width,(int)height);
	    getContentPane().setBackground(new Color(95, 158, 160));
	    getContentPane().setLayout(new CardLayout(0, 0));
	    windowPanel.setBackground(new Color(95, 158, 160));
	    getContentPane().add(windowPanel, "name_5911066010503018");
	    WelcomePanel panel = new WelcomePanel(this);
		MainPanel panelAfterCreate = new MainPanel(this);
        windowPanel.add(panel, "Welcome Panel");
        windowPanel.add(panelAfterCreate, "Panel after create command");
	    setLocationRelativeTo(null); //set window at center of the screen
	    setVisible(true); 
	}
}