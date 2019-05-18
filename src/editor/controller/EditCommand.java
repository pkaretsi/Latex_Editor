/*This class implements edit command. It updates document contents
 * every time we delete/insert something from/to textArea.
 */

package editor.controller;

import editor.model.Document;

public class EditCommand implements Command{

	private LatexEditorController controller;

	public EditCommand(LatexEditorController controller){
		this.controller = controller;
	}
	
	@Override
	public void execute(){
		String action = controller.getGuiAction();
		String tokens[] = action.split(" ");
		String text = action.replace(tokens[0] + " ", "");
		if(text.equals("SaveVersion")){
			if(controller.getVersionManager().isEnabled()){
				Document newDocument = new Document(controller.getCurrentDocument());
				int version;
				try {
					int size = controller.getVersionManager().getStrategy().getEntireHistory().size();
					if(size != Integer.parseInt(controller.getCurrentDocument().getVersionID())-1){
						version = Integer.parseInt(newDocument.getVersionID()) + 1;
						controller.getCurrentDocument().setVersionID(String.valueOf(version));
					}
					version = Integer.parseInt(newDocument.getVersionID()) + 1;
					newDocument.setVersionID(String.valueOf(version));
					controller.getVersionManager().setCurrentVersion(controller.getCurrentDocument());
					controller.setCurrentDocument(newDocument);
					System.out.println("Size is " + controller.getVersionManager().getStrategy().getEntireHistory().size());
					/////
				}
				catch (NumberFormatException e)
				{
				   e.printStackTrace();
				}
			}
		}
		else if(text.equals("Rollback")){
			controller.setStringReturned(controller.getCurrentDocument().getContents());
			return;
		}
		else{ //cut, copy, paste, insertCaretPosition
			controller.getCurrentDocument().setContents(text);
		}
		controller.setStringReturned(controller.getCurrentDocument().getContents());
	}

}
