package window.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EditorView initialW;
	
	public WelcomePanel(EditorView w){
		this.initialW = w;
		initPanel();
	}

	private void initPanel(){	
	    this.setBackground(new Color(95, 158, 160));
	    try{
	    	BufferedImage latexLogo = ImageIO.read(new File("logo.png"));
		    JLabel picLabel = new JLabel(new ImageIcon(latexLogo));
			picLabel.setLabelFor(picLabel);
			picLabel.setBounds(475, 267, 400, 300);
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
	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   double width = screenSize.getWidth();
	   JMenu createMenu = new JMenu("Create");
	   createMenu.setPreferredSize(new Dimension((int)width/2, createMenu.getPreferredSize().height));
	   createMenu.setHorizontalAlignment(SwingConstants.LEFT);
	   createMenu.setForeground(new Color(0, 0, 0));
	   createMenu.setFont(new Font("Calibri", Font.PLAIN, 16));
	   final JMenuItem loadMenu = new JMenuItem("Load"); 
	   loadMenu.setPreferredSize(new Dimension((int)width/2, loadMenu.getPreferredSize().height));
	   loadMenu.setActionCommand("Initial Load");
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
	    menuBar.add(loadMenu);
		panel.add(menuBar, BorderLayout.NORTH);
	    this.setVisible(true);   
   }

	public EditorView getInitialW() {
		return initialW;
	}
	
	private class LoadFileListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout) initialW.getWindowPanel().getLayout();
			cardLayout.show(initialW.getWindowPanel(), "Panel after create command");
			ControlActionListener cal = new ControlActionListener(initialW);
			cal.actionPerformed(e);
		}
	}
}
