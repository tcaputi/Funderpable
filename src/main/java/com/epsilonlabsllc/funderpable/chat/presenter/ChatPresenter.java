package com.epsilonlabsllc.funderpable.chat.presenter;

import java.util.Date;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.vaadin.artur.icepush.ICEPush;

import com.epsilonlabsllc.funderpable.chat.event.ChatInputEvent;
import com.epsilonlabsllc.funderpable.chat.event.ChatUpdateEvent;
import com.epsilonlabsllc.funderpable.chat.view.IChatView;

public class ChatPresenter extends Presenter<IChatView> {
	private static final long serialVersionUID = 9043221693367631201L;

	private long sessionId;
	private String username;
	private ICEPush pusher;

	private boolean initialized = false;

	@EventListener(event = ChatInputEvent.class)
	public void onChatInputEvent(ChatInputEvent e) {
		if (this.initialized) {
			// Catch the event from the view, tell all mah frenz
			dispatch(new ChatUpdateEvent(this.sessionId, this.username, e.getMessage(), (new Date()).toString()), EventScope.UNIVERSAL);
		} else {
			throw new RuntimeException("This prez ain't init baybee.");
		}
	}

	@EventListener(event = ChatUpdateEvent.class)
	public void onChatUpdate(ChatUpdateEvent e) {
		getView().addChatLine(e.getUserName(), e.getMessage(), e.getTimeStamp());
		if (e.isForeign()) {
			// If its foreign we need to push poop
			this.pusher.push();
		}
	}
	
	public void init(long sessionId, String username, ICEPush pusher) {
		this.sessionId = sessionId;
		this.username = username;
		this.pusher = pusher;
		// Mark initialized
		this.initialized = true;
	}
}
