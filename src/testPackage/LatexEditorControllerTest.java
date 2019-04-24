package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import editor.controller.LatexEditorController;

public class LatexEditorControllerTest {
	
	@Test
	public void testLatexEditorController() {
		//when creating a controller, expect currentDocument to be null
		LatexEditorController controller = new LatexEditorController();
		assertNotNull("Not null docManager",controller.getDocumentManager());
		assertNotNull(controller.getVersionManager());
		assertNull(controller.getCurrentDocument());
		assertNotNull(controller.getVersionFactory());
		assertNull(controller.getGuiAction());
	}
	

	@Test
	public final void testGetGuiAction(){
		LatexEditorController cont = new LatexEditorController();
		cont.setGuiAction("save");
		assertEquals("gui action takes value", "save", cont.getGuiAction());
	}

}
