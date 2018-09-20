package com.codecool.expertsystem;

import java.util.TreeMap;
import java.util.Set;

public class Fact{
    private String id;
    private String description;
    private TreeMap<String, Boolean> facts;


    Fact(String id, String description){
        this.id = id;
        this.description = description;
	    facts = new TreeMap<String, Boolean>();
    }


    public String getId(){
        return id;
    }

    public Set<String> getIdSet(){
        return facts.keySet();
    }
    

    public void setFactValueById(String id, boolean value){
        facts.put(id, value);
    }


    public boolean getValueById(String id){
        return facts.get(id);
    }


    public String getDescription(){
        return description;
    }
    
}
