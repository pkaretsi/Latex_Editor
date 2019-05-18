package window.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import editor.controller.HistoryHandler;
import editor.controller.LatexEditorController;


public class EditorView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel windowPanel = new JPanel(new CardLayout());
	private JTextArea textArea;
	private LatexEditorController controller;
	private boolean switched;
	
	public boolean isSwitched() {
		return switched;
	}

	public EditorView(){
		super("Latex Editor");
		controller = new LatexEditorController();
		switched = false;
		initComponents();
		this.addWindowListener(new EditorWindowAdapter(this)); 
	}
	
	private class EditorWindowAdapter extends WindowAdapter{
		
		private EditorView window;
		
		public EditorWindowAdapter(EditorView w){
			window = w;
		}
		
		public void windowClosing(java.awt.event.WindowEvent e) {
        	if(window.isSwitched()){
        		HistoryHandler hs = new HistoryHandler("closing", window);
    			hs.saveAndHandleHistory();
        	}
		}
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
	
	//Setters and Getters
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
}