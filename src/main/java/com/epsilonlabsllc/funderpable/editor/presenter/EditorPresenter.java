package com.epsilonlabsllc.funderpable.editor.presenter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.vaadin.aceeditor.collab.DocDiff;
import org.vaadin.aceeditor.collab.gwt.shared.Doc;
import org.vaadin.artur.icepush.ICEPush;
import org.vaadin.diffsync.Shared;

import com.epsilonlabsllc.funderpable.EditorSession;
import com.epsilonlabsllc.funderpable.editor.event.SwitchFileEvent;
import com.epsilonlabsllc.funderpable.editor.view.IEditorView;
import com.epsilonlabsllc.funderpable.filebroswer.event.FileOpenEvent;
import com.epsilonlabsllc.funderpable.header.event.SaveFileEvent;

public class EditorPresenter extends Presenter<IEditorView>{
	private static final long serialVersionUID = 2361280631532615525L;

	private EditorSession es;
	private File currentFile;
	private ICEPush pusher;

	public void init(EditorSession es, ICEPush pusher){
		this.es = es;
		this.pusher = pusher;
		this.currentFile = null;
	}

	@EventListener(event = SaveFileEvent.class)
	public void saveFile(SaveFileEvent event){
		if(currentFile == null) throw new NullPointerException("You cant save to null stupid");
		try{
			FileWriter fstream = new FileWriter(currentFile);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(es.getShared(currentFile).getValue().getText());
			out.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@EventListener(event = SwitchFileEvent.class)
	public void onSwitchFile(SwitchFileEvent event){
		this.currentFile = event.getFile();
	}

	@EventListener(event = FileOpenEvent.class)
	public void openFile(FileOpenEvent event) throws IOException{
		currentFile = event.getFile();
		Shared<Doc, DocDiff> sharedText = new Shared<Doc, DocDiff>(new Doc(readFileToString(currentFile)));
		getView().createEditor(currentFile, sharedText, pusher);
		es.addFile(currentFile, sharedText, this);
	}

	private String readFileToString(File file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));;
		String currentLine;
		StringBuilder builder = new StringBuilder();
		while ((currentLine = br.readLine()) != null) {
			builder.append(currentLine);
		}
		br.close();
		return builder.toString();
	}
}
