package com.epsilonlabsllc.funderpable.collaborators.event;

import org.pakhama.vaadin.mvp.event.impl.Event;

import com.epsilonlabsllc.funderpable.model.User;

public class ProjectSessionLeaveEvent extends Event{
	private static final long serialVersionUID = -485390229261381581L;

	private User user;
	
	public ProjectSessionLeaveEvent(){
	}
	
	public ProjectSessionLeaveEvent(User user){
		this.setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
