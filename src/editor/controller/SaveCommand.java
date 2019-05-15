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
			if(fi.exists()) { 
				controller.setStringReturned("Filename already exists");
				return;
			}
			String filename = j.getSelectedFile().getName();
			controller.setFileName(filename);
			controller.getCurrentDocument().save(fi);
			controller.setLastContentsSaved(controller.getCurrentDocument().getContents());
			//controller.setFirstDocument(controller.getCurrentDocument().clone(controller.getCurrentDocument()));
			//every time you save permanently a document, update the first version of rollbacks 
		}
	}
	
	//private void renameHistory

}

