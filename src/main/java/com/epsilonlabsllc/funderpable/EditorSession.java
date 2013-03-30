package com.epsilonlabsllc.funderpable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.vaadin.aceeditor.collab.DocDiff;
import org.vaadin.aceeditor.collab.gwt.shared.Doc;
import org.vaadin.diffsync.Shared;

public class EditorSession {

	private Shared<Doc, DocDiff> sharedText;
	private File file;
	
	public EditorSession(File file){
		this.file = file;
		readFile();
	}
	
	public void readFile() {
		BufferedReader br = null;
		try {
			String text = "";
			String sCurrentLine;
			br = new BufferedReader(new FileReader(file));
			while ((sCurrentLine = br.readLine()) != null) {
				text += sCurrentLine + "\n";
			}
			br.close();
			sharedText = new Shared<Doc, DocDiff>(new Doc(text));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public void saveFile()  {
		try{
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(sharedText.getValue().getText());
			out.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public Shared<Doc, DocDiff> getSharedText() {
		return sharedText;
	}
	public void setSharedText(Shared<Doc, DocDiff> sharedText) {
		this.sharedText = sharedText;
	}
	public File getFile() {
		return file;
	}
	public  void setFile(File file) {
		this.file = file;
	}
}
