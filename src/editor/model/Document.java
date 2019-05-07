package editor.model;
//Based on version 1.0 with small changes at getters and setters and a copy constructor

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Document implements Serializable {
	
	private String author;
	private String date;
	private String copyright;
	private String versionID;
	private String DocumentType = "Other";
	private String contents;
	private boolean firstChange = false;

	
	//constructors
	public Document(Document other){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy_HH-mm-ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		this.author = other.author;
		this.contents = other.contents;
		this.copyright = other.copyright;
		this.date = dtf.format(localDateTime);
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
		
	public Document(String author,String date,String copyright,String versionID,String contents,String DocumentType) {
		this.author=author;
		this.date=date;
		this.copyright=copyright;
		this.versionID=versionID;
		this.contents=contents;
		this.DocumentType=DocumentType;
	}
	
	public boolean isFirstChange() {
		return firstChange;
	}
	
	public void setFirstChange(boolean firstChange) {
		this.firstChange = firstChange;
	}
	
	public String getDocumentType() {
		return DocumentType;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getVersionID() {
		return versionID;
	}
	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}
	
	public void setDocumentType(String documentType) {
		DocumentType = documentType;
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
	
}
