package editor.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import editor.model.Document;
import editor.model.VersionStrategy;

public class EnableVersionManagementCommand implements Command {
	private LatexEditorController controller;

	public EnableVersionManagementCommand(LatexEditorController controller){
		this.controller = controller;
	}
	
	@Override
	public void execute() {
		if(controller.getDocumentManager().isLoaded()){
			loadHistory();
			controller.getDocumentManager().setLoaded(false);
		}
		controller.getVersionManager().enable();
		VersionStrategy vs = controller.getVersionFactory().createStrategy("Volatile");
		VersionStrategy previousStrategy = controller.getVersionManager().getStrategy();
		if (previousStrategy != null){
			vs.getEntireHistory().addAll(previousStrategy.getEntireHistory());
		}
		controller.getVersionManager().setStrategy(vs);
		//}
		controller.setStringReturned(controller.getCurrentDocument().getContents());
	}
	
	private void loadHistory(){
		String pdir = System.getProperty("user.dir") + '\\' + controller.getFileName();
		System.out.println("Present Project Directory : " + pdir);
		File f = new File(pdir);
		if(f.exists() && f.isDirectory()) { 
		    System.out.println("Exists directory");
			File[] candidates = f.listFiles();
			String filename; 
			FileInputStream file;
			ObjectInputStream in;
			Document doc;
		    for(File c : candidates){
		    	String path = System.getProperty("user.dir")+'\\'+c.getName();
		    	c.renameTo(new File(path));
		    }
		}
		f.delete();
	}
			/*	try{
					file = new FileInputStream(filename);
					in = new ObjectInputStream(c);
					doc = (Document)in.readObject();
					in.close();
					file.close();
					File f = new File(filename);
					f.delete(); //delete files from disk
					entireHistory.add(doc);
				}
				catch(IOException ex){ 
					ex.printStackTrace();
		        }  
		        catch(ClassNotFoundException ex){
		            System.out.println("ClassNotFoundException is caught"); 
		            ex.printStackTrace();
		        }
			}
		}
		/*File dir = new File(pdir);
		File[] candidates = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				System.out.println(pathname.getName().startsWith(subname));
				return pathname.getName().startsWith(subname);
			}
		});
	}*/
	
}
