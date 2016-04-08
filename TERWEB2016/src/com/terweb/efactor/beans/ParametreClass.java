package com.terweb.efactor.beans;

import java.util.ArrayList;
import java.util.HashMap;

public class ParametreClass {
	private String userID;
	private String biomass;
	private String experience;
    private HashMap<String,ArrayList<String>> topics ;
    private HashMap<String,ArrayList<String>> relations ;
    
    
    
    public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getBiomass() {
		return biomass;
	}
	public void setBiomass(String biomass) {
		this.biomass = biomass;
	}
	public HashMap<String, ArrayList<String>> getTopics() {
		return topics;
	}
	public void setTopics(HashMap<String, ArrayList<String>> topics) {
		this.topics = topics;
	}
	public HashMap<String, ArrayList<String>> getRelations() {
		return relations;
	}
	public void setRelations(HashMap<String, ArrayList<String>> relations) {
		this.relations = relations;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public void addtopics(String tpoic, ArrayList<String> valeurs){
		this.topics = new HashMap<String,ArrayList<String>>();
		this.topics.put(tpoic, valeurs);
}

	public void addrelations(String relation, ArrayList<String> valeurs){
		this.relations = new HashMap<String,ArrayList<String>>();
		this.relations.put(relation, valeurs);
}
	
}
