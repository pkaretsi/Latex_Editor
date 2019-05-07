package editor.controller;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SaveCommand implements Command {

	private LatexEditorController controller;

	public SaveCommand(LatexEditorController controller){
		this.controller = controller;
	}
	
	@Override
	public void execute() {
		JFileChooser j = new JFileChooser("C:"); 
		FileNameExtensionFilter texfilter = new FileNameExtensionFilter("LaTeX files", "tex");
        j.setFileFilter(texfilter);
		int r = j.showSaveDialog(null); 
		if (r == JFileChooser.APPROVE_OPTION) { 
			File fi = new File(j.getSelectedFile().getAbsolutePath() + ".tex");
			controller.getCurrentDocument().save(fi);
			controller.setLastContentsSaved(controller.getCurrentDocument().getContents());
		}
	}

}

