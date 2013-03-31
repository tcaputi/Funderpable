package com.epsilonlabsllc.funderpable.collaborators.presenter;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.vaadin.artur.icepush.ICEPush;

import com.epsilonlabsllc.funderpable.collaborators.event.ProjectSessionJoinEvent;
import com.epsilonlabsllc.funderpable.collaborators.event.ProjectSessionLeaveEvent;
import com.epsilonlabsllc.funderpable.collaborators.view.ICollaboratorsView;

public class CollaboratorsPresenter extends Presenter<ICollaboratorsView>{
	private static final long serialVersionUID = 7105573403984020137L;
	
	private ICEPush pusher;
	
	public void init(ICEPush pusher){
		this.pusher = pusher;
	}

	@EventListener(event = ProjectSessionJoinEvent.class)
	public void onUserLogin(ProjectSessionJoinEvent event){
		getView().login(event.getUser());
		if(event.isForeign()) pusher.push();
	}

	@EventListener(event = ProjectSessionLeaveEvent.class)
	public void onUserLogout(ProjectSessionLeaveEvent event){
		getView().logout(event.getUser());
		if(event.isForeign()) pusher.push();
	}
}
