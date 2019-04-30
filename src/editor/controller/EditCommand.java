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
		//if(tokens[1].equals("SaveVersion")){
			if(controller.getVersionManager().isEnabled()){
				//System.out.println("Should save a version now");
				Document newDocument = new Document(controller.getCurrentDocument());
				int version;
				try {
				   version = Integer.parseInt(newDocument.getVersionID()) + 1;
				   //System.out.println("New version here " + version);
				   newDocument.setVersionID(String.valueOf(version));
				   controller.getVersionManager().setCurrentVersion(controller.getCurrentDocument());
				   controller.setCurrentDocument(newDocument);
				   System.out.println("Size is " + controller.getVersionManager().getStrategy().getEntireHistory().size());
				}
				catch (NumberFormatException e)
				{
				   e.printStackTrace();
				}
			}
		}
		else if(text.equals("Rollback")){
		//else if(tokens[1].equals("Rollback")){
			//System.out.println("new hereee \n" + controller.getCurrentDocument().getContents());
			controller.setStringReturned(controller.getCurrentDocument().getContents());
		}
		else{ //cut, copy, paste
			controller.getCurrentDocument().setContents(text);
			controller.setStringReturned(controller.getCurrentDocument().getContents());
		}
	}

}
