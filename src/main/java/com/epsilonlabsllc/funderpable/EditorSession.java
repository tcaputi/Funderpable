package com.epsilonlabsllc.funderpable;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.event.IEventDispatcher;
import org.vaadin.aceeditor.collab.DocDiff;
import org.vaadin.aceeditor.collab.gwt.shared.Doc;
import org.vaadin.diffsync.Shared;

public class EditorSession {

	private long sessionId = -1;
	private HashMap<File, Shared<Doc, DocDiff>> sharedTextMap = new HashMap<File, Shared<Doc, DocDiff>>();
	
	public EditorSession(long sessionId){
		this.sessionId = sessionId;
	}
	
	public Set<File> getFiles(){
		return sharedTextMap.keySet();
	}
	
	public Shared<Doc, DocDiff> getShared(File file){
		return sharedTextMap.get(file);
	}
	
	public void addFile(File file, Shared<Doc, DocDiff> shared, IEventDispatcher dispatcher){
		sharedTextMap.put(file, shared);
		dispatcher.dispatch(new FileAddedEvent(file, sessionId), EventScope.UNIVERSAL);
	}
	
	public void removeFile(File file){
		sharedTextMap.remove(file);
	}
}
