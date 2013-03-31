package com.epsilonlabsllc.funderpable.model;

import java.util.HashSet;

import com.epsilonlabsllc.funderpable.EditorSession;

public class ProjectSession {
	private long sessionId;
	private Project project;
	private EditorSession es;
	private HashSet<User> users = new HashSet<User>();
	
	public ProjectSession(long sessionId, Project project) {
		this.sessionId = sessionId;
		this.project = new Project();
		this.project.setName("McHardbody");
//		this.project = project;
		this.es = new EditorSession(sessionId);
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public EditorSession getEs() {
		return es;
	}
	public void setEs(EditorSession es) {
		this.es = es;
	}
	public HashSet<User> getUsers() {
		return users;
	}
	public void setUsers(HashSet<User> users) {
		this.users = users;
	}
}
