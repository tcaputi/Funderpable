package com.epsilonlabsllc.funderpable.chat.view;

import org.pakhama.vaadin.mvp.view.IView;

public interface IChatView extends IView {
	public void addChatLine(String userName, String message, String timeStamp);
}
