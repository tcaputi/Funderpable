package com.epsilonlabsllc.funderpable;

import java.util.LinkedList;
import java.util.List;

public class ChatSession {
	private List<EditingUser> currentUsers = new LinkedList<EditingUser>();
	private List<ChatEntry> chatLog = new LinkedList<ChatEntry>();
	
	public ChatSession(){
	}
	
	public void logIn(String username){
		currentUsers.add(new EditingUser(username));
	}
	public void logOut(String username){
		currentUsers.remove(new EditingUser(username));
	}
	public List<EditingUser> getCurrentUsers() {
		return currentUsers;
	}
	public void setCurrentUsers(List<EditingUser> currentUsers) {
		this.currentUsers = currentUsers;
	}
	public List<ChatEntry> getChatLog() {
		return chatLog;
	}
	public void setChatLog(List<ChatEntry> chatLog) {
		this.chatLog = chatLog;
	}
}
