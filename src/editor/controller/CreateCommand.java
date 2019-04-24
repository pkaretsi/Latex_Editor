/*This class implements document creation.
 * It uses the document manager which is responsible
 * for the documents' cloning.
 * */

package editor.controller;

import editor.model.Document;

public class CreateCommand implements Command{
	
	private LatexEditorController controller;

	public CreateCommand(LatexEditorController controller){
		this.controller = controller;
	}
	
	@Override
	public void execute(){
		String action = controller.getGuiAction();
		String tokens[] = action.split(" ");
		Document newDoc = controller.getDocumentManager().createDocument(tokens[1]);
		controller.setCurrentDocument(newDoc);
		controller.setStringReturned(controller.getCurrentDocument().getContents());
	}
}
