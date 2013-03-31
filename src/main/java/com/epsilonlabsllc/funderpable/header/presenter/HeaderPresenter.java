package com.epsilonlabsllc.funderpable.header.presenter;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;

import com.epsilonlabsllc.funderpable.header.event.BuildEvent;
import com.epsilonlabsllc.funderpable.header.event.SaveFileEvent;
import com.epsilonlabsllc.funderpable.header.view.IHeaderView;
import com.epsilonlabsllc.funderpable.ide.event.NotificationEvent;

public class HeaderPresenter extends Presenter<IHeaderView>{
	private static final long serialVersionUID = 2437892955305754425L;

	private long sessionId;
	
	public void init(long sessionId){
		this.sessionId = sessionId;
	}
	
	@EventListener(event = SaveFileEvent.class)
	public void onSaveButtonClick(SaveFileEvent event){
		if(event.getSource() != this) dispatch(event, EventScope.SIBLINGS);
	}
	
	@EventListener(event = BuildEvent.class)
	public void onBuildButtonClick(BuildEvent event){
		if(event.getSource() != this) dispatch(event, EventScope.PARENT);
		dispatch(new NotificationEvent(sessionId, "Build Requested"), EventScope.UNIVERSAL);
	}
}
