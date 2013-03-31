package com.epsilonlabsllc.funderpable.filebroswer.presenter;

import java.io.File;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;

import com.epsilonlabsllc.funderpable.filebroswer.event.FileOpenEvent;
import com.epsilonlabsllc.funderpable.filebroswer.event.NodeExpandEvent;
import com.epsilonlabsllc.funderpable.filebroswer.view.IFileBrowserView;

public class FileBrowserPresenter extends Presenter<IFileBrowserView>{
	private static final long serialVersionUID = -3637268767473184414L;
	
	public void init(File directory){
		if(directory != null) getView().populateNodeOrOpen(directory.getAbsolutePath(), null);
	}
	
	@EventListener(event = NodeExpandEvent.class)
	public void onNodeExpanded(NodeExpandEvent event){
		getView().populateNodeOrOpen(event.getFileName(), event.getItem());
	}
	
	@EventListener(event = FileOpenEvent.class)
	public void onFileOpened(FileOpenEvent event){
		if(event.getSource() != this) dispatch(event, EventScope.SIBLINGS);
	}
}
