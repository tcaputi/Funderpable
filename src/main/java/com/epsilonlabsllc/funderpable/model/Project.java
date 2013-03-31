package com.epsilonlabsllc.funderpable.model;

import java.util.HashSet;

public class Project extends BaseEntity {
	private String name;
	
	private HashSet<Build> builds = new HashSet<Build>();

	private HashSet<User> collaborators = new HashSet<User>();
	
	private HashSet<BuildConfiguration> buildConfiguations = new HashSet<BuildConfiguration>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashSet<User> getCollaborators() {
		return collaborators;
	}

	public HashSet<Build> getBuilds() {
		return builds;
	}

	public HashSet<BuildConfiguration> getBuildConfiguations() {
		return buildConfiguations;
	}
}
