package com.epsilonlabsllc.funderpable.header.presenter;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;

import com.epsilonlabsllc.funderpable.header.event.SaveFileEvent;
import com.epsilonlabsllc.funderpable.header.view.IHeaderView;

public class HeaderPresenter extends Presenter<IHeaderView>{
	private static final long serialVersionUID = 2437892955305754425L;

	public void init(String projectName){
		getView().setProjectName(projectName);
	}
	
	@EventListener(event = SaveFileEvent.class)
	public void onSaveButtonClick(SaveFileEvent event){
		if(event.getSource() != this) dispatch(event, EventScope.SIBLINGS);
	}
}
