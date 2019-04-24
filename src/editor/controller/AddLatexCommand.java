/*This class implements the addition of a Latex Command
 * from the menu list. It also sets the right restrictions
 * when having Letters or Articles.
 */

package editor.controller;

import java.util.HashMap;

public class AddLatexCommand implements Command {
	
	private String content;
	private LatexEditorController controller;
	private HashMap<String, String> commandContents;

	public AddLatexCommand(LatexEditorController controller){
		this.controller = controller;
		commandContents = new HashMap<String, String>();
		setMap();
	}
	
	private void setMap(){
		commandContents.put("Chapter", "\\chapter{...}");
		commandContents.put("Section", "\\section{...}");
		commandContents.put("Subsection", "\\subsection{...}");
		commandContents.put("Subsubsection", "\\subsubsection{...}");
		commandContents.put("BulletList", "\\begin{itemize}\n\\item ...\n"
				+ "\\item ...\n\\end{itemize}");
		commandContents.put("EnumerationList", "\\begin{enumerate}\n"
				+ "\\item ...\n\\item ...\n\\end{enumerate}");
		commandContents.put("Table", "\\begin{table}\n\\caption{....}\\label{...}\n"
				+ "\\begin{tabular}{|c|c|c|}\n \\hline\n... &...&...\\\\\n... &...&...\\\\\n"
				+ "... &...&...\\\\\n \\hline\n\\end{tabular}\n\\end{table}");
		commandContents.put("Figure", "\\begin{figure}\n\\includegraphics[width=...,height=...]{...}\n"
				+ "\\caption{....}\\label{...}\n\\end{figure}");
	}

	@Override
	public void execute() {
		//versionKept = true;
		String action = controller.getGuiAction();
		String tokens[] = action.split(" ");
		clearContent();
		if(commandContents.containsKey(tokens[1])){
			if(controller.getCurrentDocument().getDocumentType().equals("letter")){
				//set pop up window and don't add anything
				//System.out.println("Cannot add anything in a letter");
				content = "";
				controller.setStringReturned(content);
				return;
			}
			if(controller.getCurrentDocument().getDocumentType().equals("article") 
					&& tokens[1].equals("Chapter")){
				//set pop up window and don't add anything
				//System.out.println("Cannot add chapter in an article");
				content = "";
				controller.setStringReturned(content);
				return;
			}
			content = commandContents.get(tokens[1]);
		}
		controller.setStringReturned(content);
	}
	
	private void clearContent(){
		content = "";
	}
}
