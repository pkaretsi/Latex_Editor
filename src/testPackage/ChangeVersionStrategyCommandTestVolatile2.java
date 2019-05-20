/*This test initializes versionManager's 
 * strategy with a StableVersionStrategy object
 * and executes ChangeVersionStrategyCommand.
 * The expected result is a VolatileVersionStrategy
 * object.
 */

package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.Command;
import editor.controller.LatexEditorController;
import editor.model.Document;
import editor.model.VersionStrategy;

public class ChangeVersionStrategyCommandTestVolatile2 {

	@Test
	public void test() {
		LatexEditorController controller = new LatexEditorController();
		Document newDoc = new Document("me", "unknown", "2", "\\end{document}", "book");
		controller.setCurrentDocument(newDoc);
		controller.getVersionManager().enable(); //Enable version tracking mechanism
		VersionStrategy vs = controller.getVersionFactory().createStrategy("Stable");
		controller.getVersionManager().setStrategy(vs);
		vs = controller.getVersionManager().getStrategy();
		//Change Strategy to Volatile
		Command command = controller.getCommandFactory().createCommands("ChangeVersionsStrategy", controller);
		controller.setGuiAction("ChangeVersionsStrategy Volatile");
		command.execute();
		assertNotEquals(vs, controller.getVersionManager().getStrategy());
	}

}
