package window.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import javax.swing.JLabel;


public class MainPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EditorView welcomeW;

	public MainPanel(EditorView w){
		welcomeW = w;
		initNewPanel();
	}

	private void createCommandList(JPanel p){
		final JMenuBar commandsList = new JMenuBar();
		commandsList.setBackground(new Color(248, 248, 255));
	    JMenuItem chapterMenu = new JMenuItem("Chapter");
	    chapterMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
		JMenuItem sectionMenu = new JMenuItem("Section"); 
		sectionMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
		JMenuItem subsectionMenu = new JMenuItem("Subsection");
		subsectionMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
		JMenuItem subsubsectionMenu = new JMenuItem("Subsubsection");
		subsubsectionMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
		JMenuItem bulletMenu = new JMenuItem("Bullet list");
		bulletMenu.setActionCommand("BulletList");
		bulletMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
		JMenuItem enumerateMenu = new JMenuItem("Enumeration list");
		enumerateMenu.setActionCommand("EnumerationList");
		enumerateMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
		JMenuItem tableMenu = new JMenuItem("Table");
		tableMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
		JMenuItem figureMenu = new JMenuItem("Figure");
		figureMenu.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel lblAdd = new JLabel("Add...");
		lblAdd.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		commandsList.add(lblAdd);
		commandsList.add(chapterMenu);
		commandsList.add(sectionMenu); 
		commandsList.add(subsectionMenu);
		commandsList.add(subsubsectionMenu);
		commandsList.add(bulletMenu);
		commandsList.add(enumerateMenu);
		commandsList.add(tableMenu);
		commandsList.add(figureMenu);
		p.add(commandsList,BorderLayout.WEST);
		GridLayout grid = new GridLayout(0,1);
		commandsList.setLayout(grid);
		
		JMenuBar editCommands = new JMenuBar();
	    editCommands.setFont(new Font("Calibri", Font.PLAIN, 14));
	    p.add(editCommands, BorderLayout.EAST);
	    
	    JLabel lblEditContent = new JLabel("Edit content");
	    editCommands.add(lblEditContent);
	    lblEditContent.setHorizontalAlignment(SwingConstants.CENTER);
	    lblEditContent.setFont(new Font("Calibri", Font.PLAIN, 14));
	    JMenuItem cut = new JMenuItem("Cut");
	    cut.setFont(new Font("Calibri", Font.PLAIN, 14));
	    editCommands.add(cut);
	    JMenuItem copy = new JMenuItem("Copy");
	    editCommands.add(copy);
	    copy.setFont(new Font("Calibri", Font.PLAIN, 14));
	    JMenuItem paste = new JMenuItem("Paste");
	    editCommands.add(paste);
	    paste.setFont(new Font("Calibri", Font.PLAIN, 14));
	    JMenuItem selectAll = new JMenuItem("Select All");
	    selectAll.setActionCommand("SelectAll");
	    editCommands.add(selectAll);
	    selectAll.setFont(new Font("Calibri", Font.PLAIN, 14));
	    GridLayout gridR = new GridLayout(0,1);
		editCommands.setLayout(gridR);
		
		chapterMenu.addActionListener(new CommandListener(welcomeW));
		sectionMenu.addActionListener(new CommandListener(welcomeW));
		subsectionMenu.addActionListener(new CommandListener(welcomeW));
		subsubsectionMenu.addActionListener(new CommandListener(welcomeW));
		bulletMenu.addActionListener(new CommandListener(welcomeW));
		enumerateMenu.addActionListener(new CommandListener(welcomeW));
		tableMenu.addActionListener(new CommandListener(welcomeW));
		figureMenu.addActionListener(new CommandListener(welcomeW));
		cut.addActionListener(new EditListener(welcomeW));
		copy.addActionListener(new EditListener(welcomeW));
		paste.addActionListener(new EditListener(welcomeW));
		selectAll.addActionListener(new EditListener(welcomeW));
		setVisible(true);   
	}
	
	private void createControlPanel(JPanel p){
		JPanel controlPanel = new JPanel();
	    FlowLayout fl_controlPanel = new FlowLayout();
	    fl_controlPanel.setHgap(10);
	    fl_controlPanel.setVgap(15);
	    fl_controlPanel.setAlignment(FlowLayout.LEFT);
	    controlPanel.setLayout(fl_controlPanel);
	    p.add(controlPanel, BorderLayout.NORTH);
	    JButton saveButton = new JButton("Save");
	    saveButton.setFont(new Font("Calibri", Font.PLAIN, 14));
	    JButton rollbackButton = new JButton("Rollback");
	    rollbackButton.setFont(new Font("Calibri", Font.PLAIN, 14));	    
	    JButton loadButton = new JButton("Load");
	    loadButton.setFont(new Font("Calibri", Font.PLAIN, 14));
	    
	    JButton cancelRollbackButton = new JButton("Cancel Rollback");
	    cancelRollbackButton.setFont(new Font("Calibri", Font.PLAIN, 14));	
	    cancelRollbackButton.setActionCommand("CancelRollback");

	    controlPanel.add(loadButton);
	    controlPanel.add(saveButton);
	    controlPanel.add(rollbackButton);
	    controlPanel.add(cancelRollbackButton);
	    saveButton.addActionListener(new ControlActionListener(welcomeW));
	    loadButton.addActionListener(new ControlActionListener(welcomeW));
	    rollbackButton.addActionListener(new ControlActionListener(welcomeW));
	    cancelRollbackButton.addActionListener(new ControlActionListener(welcomeW));
	    JCheckBox versionButton = new JCheckBox("Enable Version Tracking Mechanism");
	    versionButton.setActionCommand("Enable VersionStrategy"); 
	    versionButton.setSelected(false);
	    controlPanel.add(versionButton);
        JRadioButton stableButton = new JRadioButton("Stable Strategy");
        stableButton.setActionCommand("Stable");
        JRadioButton volatileButton = new JRadioButton("Volatile Strategy");
        volatileButton.setActionCommand("Volatile");
        JButton saveVersionButton = new JButton("Submit Changes");
        saveVersionButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        saveVersionButton.setActionCommand("SaveVersion");
	    controlPanel.add(saveVersionButton);
	    saveVersionButton.addActionListener(new ControlActionListener(welcomeW));

        ButtonGroup group = new ButtonGroup();
        group.add(volatileButton);
        group.add(stableButton);
        controlPanel.add(volatileButton);
        controlPanel.add(stableButton);
        versionButton.addItemListener(new VersionItemListener(volatileButton, group));
        volatileButton.addActionListener(new VersionActionListener());
        stableButton.addActionListener(new VersionActionListener());
	}
	
	private class VersionItemListener implements ItemListener{
		private JRadioButton vButton;
		private ButtonGroup group;
		
		public VersionItemListener(JRadioButton vb, ButtonGroup g){
			vButton = vb;
			group = g;
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			String action;
			if (e.getStateChange() == ItemEvent.DESELECTED){
				group.clearSelection();
				action = "DisableVersionsManagement ";
			}
			else{
	        	vButton.setSelected(true);
	        	action = "EnableVersionsManagement ";
			}
			welcomeW.getController().enact(action);
		}
		
	}
	
	private class VersionActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Change state of " + e.getActionCommand());
			String action = "ChangeVersionsStrategy" + " " + e.getActionCommand();
			welcomeW.getController().enact(action);
		}
	}
	
	private JPanel initNewPanel(){
		this.setLayout(new BorderLayout(0, 0));
		createCommandList(this);
		createControlPanel(this);
	    JTextArea ta = welcomeW.getTextArea();
	    this.add(ta, BorderLayout.CENTER);
	    JScrollPane scroll = new JScrollPane
	    		(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    this.add(scroll);
	    setVisible(true); 
	    return this;
	}
}
