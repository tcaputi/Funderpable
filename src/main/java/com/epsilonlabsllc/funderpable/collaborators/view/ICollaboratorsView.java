package com.epsilonlabsllc.funderpable.collaborators.view;

import org.pakhama.vaadin.mvp.view.IView;

import com.epsilonlabsllc.funderpable.model.User;

public interface ICollaboratorsView extends IView{
	public void login(User user);
	public void logout(User user);
}
