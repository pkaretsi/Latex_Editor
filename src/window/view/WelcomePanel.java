package window.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;


public class WelcomePanel extends JPanel{
	private EditorView initialW;
	
	public WelcomePanel(EditorView w){
		this.initialW = w;
		initPanel();
	}

	private void initPanel(){	
	    this.setBackground(new Color(95, 158, 160));
	    try{
	    	BufferedImage latexLogo = ImageIO.read(new File("latexLogo.jpg"));
		    JLabel picLabel = new JLabel(new ImageIcon(latexLogo));
			picLabel.setLabelFor(picLabel);
			picLabel.setBounds(475, 267, 400, 195);
			this.add(picLabel);
	    }
	    catch(IOException e){
	    	e.printStackTrace();
	    }
	    this.setLayout(new BorderLayout(0, 0));
		JLabel lblWelcomeToLatex = new JLabel("Welcome to LaTeX Editor");
		lblWelcomeToLatex.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToLatex.setForeground(new Color(248, 248, 255));
		lblWelcomeToLatex.setFont(new Font("Calibri", Font.BOLD, 28));
		lblWelcomeToLatex.setBounds(525, 126, 299, 30);
		this.add(lblWelcomeToLatex, BorderLayout.NORTH);
		createMenu(this);
	}

private void createMenu(JPanel panel){
	   final JMenuBar menuBar = new JMenuBar();
	   menuBar.setForeground(new Color(0, 0, 0));
	   menuBar.setFont(new Font("Calibri", Font.PLAIN, 16));
	   menuBar.setBackground(new Color(192, 192, 192));
	   JMenu createMenu = new JMenu("Create");
	   createMenu.setHorizontalAlignment(SwingConstants.LEFT);
	   createMenu.setForeground(new Color(0, 0, 0));
	   createMenu.setFont(new Font("Calibri", Font.PLAIN, 16));
	   final JMenuItem loadMenu = new JMenuItem("Load"); 
	   loadMenu.setHorizontalAlignment(SwingConstants.LEFT);
	   loadMenu.setForeground(new Color(0, 0, 0));
	   loadMenu.setFont(new Font("Calibri", Font.PLAIN, 16));
	   JMenuItem reportItem = new JMenuItem("Report");
	   reportItem.setFont(new Font("Calibri", Font.PLAIN, 14));
	   reportItem.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	   reportItem.setActionCommand("Create Report");
	   JMenuItem bookItem = new JMenuItem("Book");
	   bookItem.setFont(new Font("Calibri", Font.PLAIN, 14));
	   bookItem.setActionCommand("Create Book");
	   JMenuItem articleItem = new JMenuItem("Article");
	   articleItem.setFont(new Font("Calibri", Font.PLAIN, 14));
	   articleItem.setActionCommand("Create Article");
	   JMenuItem letterItem = new JMenuItem("Letter");
	   letterItem.setFont(new Font("Calibri", Font.PLAIN, 14));
	   letterItem.setActionCommand("Create Letter");
	   JMenuItem emptyItem = new JMenuItem("Other");
	   emptyItem.setFont(new Font("Calibri", Font.PLAIN, 14));
	   emptyItem.setActionCommand("Create Other");
	    
	    
	    reportItem.addActionListener(new FirstMenuItemListener(initialW));
	    bookItem.addActionListener(new FirstMenuItemListener(initialW));
	    articleItem.addActionListener(new FirstMenuItemListener(initialW));
	    letterItem.addActionListener(new FirstMenuItemListener(initialW));
	    emptyItem.addActionListener(new FirstMenuItemListener(initialW));
	    
	    loadMenu.addActionListener(new LoadFileListener());
	    
	    createMenu.add(reportItem);
	    createMenu.add(bookItem);
	    createMenu.add(articleItem);
	    createMenu.add(letterItem);
	    createMenu.add(emptyItem);

	    menuBar.add(createMenu);
	    //menuBar.add(loadMenu);
		panel.add(menuBar, BorderLayout.NORTH);
	    this.setVisible(true);   
   }

	public EditorView getInitialW() {
		return initialW;
	}
	
	private class LoadFileListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			switchMenuPanel();
		}

		private void switchMenuPanel(){
		    String buffer = "";
			JFrame f = new JFrame();
			JFileChooser FileOpener = new JFileChooser("f:"); 
			FileNameExtensionFilter filter = new FileNameExtensionFilter("LaTeX files", "tex");
		    FileOpener.setFileFilter(filter);
			int r = FileOpener.showOpenDialog(null); 
			if (r == JFileChooser.APPROVE_OPTION) { 
				File fload = new File(FileOpener.getSelectedFile().getAbsolutePath()); 
				//CardLayout cardLayout = (CardLayout) window.getWindowPanel().getLayout();
				//cardLayout.show(window.getWindowPanel(), "Panel after create command");
				try { 
	                CardLayout cardLayout = (CardLayout) initialW.getWindowPanel().getLayout();
	    			cardLayout.show(initialW.getWindowPanel(), "Panel after create command");
					try {
		                Scanner scn = new Scanner(new FileInputStream(fload));
		                while (scn.hasNext()) {
		                    buffer += scn.nextLine() + "\n";
		                }
		                initialW.getTextArea().setText("");
		                initialW.getTextArea().setText(buffer);
		            } catch (FileNotFoundException ex) {
		                Logger.getLogger(EditorView.class.getName()).log(Level.SEVERE, null, ex);
		            }
				} 
				catch (Exception evt) { 
					System.out.println(evt.getMessage());
					//evt.printStackTrace();
					//should create document first before printing in textarea when loading
					JOptionPane.showMessageDialog(f, evt.getMessage()); 
				} 
			} 
			else{
				JOptionPane.showMessageDialog(f, "Operation cancelled"); 
			}
		}
	}
}
