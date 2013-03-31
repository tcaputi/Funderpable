package com.epsilonlabsllc.funderpable;

import java.io.File;

import org.pakhama.vaadin.mvp.event.impl.Event;

public class FileAddedEvent extends Event{
	private static final long serialVersionUID = 5530605971938357265L;
	
	private File file;
	private long sessionId;
	
	public FileAddedEvent(File file, long sessionId){
		this.file = file;
		this.sessionId = sessionId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
}
