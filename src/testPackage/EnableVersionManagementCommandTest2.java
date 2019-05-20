/*Test creates fake Document versions to show that
 * after enabling tracking mechanism, previously 
 * saved history is loaded. Expecting to load all
 * previous versions into entireHistory attribute.
 */

package testPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;

public class EnableVersionManagementCommandTest2 {

	@Test
	public void test() {
		ArrayList<Document> loaded = new ArrayList<Document>();
		Document newDoc;
		for(int i=0; i<5; i++){
			newDoc = new Document("me", "unknown", "2", "\\end{document} " + i, "article");
			loaded.add(newDoc);
		}
		newDoc = new Document("me", "unknown", "2", "Hello World", "article");
		LatexEditorController controller = new LatexEditorController();
		controller.setCurrentDocument(newDoc);
		controller.getVersionManager().setLoadedHistory(loaded);
		
		Command command = controller.getCommandFactory().createCommands("EnableVersionsManagement", controller);
		controller.setGuiAction("EnableVersionsManagement 1");
		command.execute();
		
		assertNotEquals(controller.getVersionManager().getStrategy().getEntireHistory().size(), 0);
	}

}
