package editor.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Document implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String author;
	private String date;
	private String copyright;
	private String versionID;
	private String DocumentType = "Other";
	private String contents;

	public Document(Document other){
		setCurrentDate();
		this.author = other.author;
		this.contents = other.contents;
		this.copyright = other.copyright;
		this.DocumentType = other.DocumentType;
		this.versionID = other.versionID;
	}
	public Document() {
		this.author="Unknown";
		this.date="";
		this.copyright="Unknown";
		this.versionID="1";
		this.contents="";
		this.DocumentType="";
	}
		
	public Document(String author, String date, String copyright, String versionID, 
			String contents, String DocumentType) {
		this.author=author;
		setCurrentDate();
		this.copyright=copyright + author;
		this.versionID=versionID;
		this.contents=contents;
		this.DocumentType=DocumentType;
	}
	
	public Document clone (Document other) {
		Document deep_copy = new Document(other);
		return deep_copy;	
	}

	public void save(File fi) {
		try { 
			FileWriter wr = new FileWriter(fi, false); 
			BufferedWriter w = new BufferedWriter(wr); 
			w.write(contents); 
			w.flush(); 
			w.close(); 
		} 
		catch (Exception evt) { 
			evt.printStackTrace();
		} 
	}
	
	//Setters and Getters
	public String getDocumentType() {
		return DocumentType;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getDate() {
		return date;
	}
	
	public void setCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy_HH-mm-ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		this.date = dtf.format(localDateTime);
	}
	
	public String getVersionID() {
		return versionID;
	}
	
	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}
}
