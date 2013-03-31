package com.epsilonlabsllc.funderpable.filebroswer.event;

import org.pakhama.vaadin.mvp.event.impl.Event;

public class NodeExpandEvent extends Event{
	
	private static final long serialVersionUID = 5899818923271391355L;
	
	private String fileName;
	private Object item;
	private boolean hasChildren;
	
	public NodeExpandEvent(){
	}
	
	public NodeExpandEvent(String fileName, Object item, boolean hasChildren) {
		this.fileName = fileName;
		this.item = item;
		this.hasChildren = hasChildren;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Object getItem() {
		return item;
	}

	public void setItem(Object item) {
		this.item = item;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	
}
