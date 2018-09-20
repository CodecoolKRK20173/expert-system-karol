package com.codecool.expertsystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class FactRepository{

    Map<String, Fact> factMap;
    Iterator<Fact> factIterator;

    FactRepository(){
        factMap = new LinkedHashMap<String, Fact>();
        factIterator = new FactIterator();
    }


    public void addFact(Fact fact){
        factMap.put(fact.getId(), fact);
    }


    public Iterator<Fact> getIterator(){
        return factIterator;
    }



    private class FactIterator implements Iterator<Fact>{

        int count;
        
        FactIterator(){
            count = 0;
        }


        public boolean hasNext() {
            return (count < factMap.size() && factMap != null);
        }

        
        public Fact next() {
            ArrayList<String> factKeys = new ArrayList<String>();

            for(Map.Entry entry : factMap.entrySet()){
                factKeys.add(entry.getKey().toString());
            }

            return factMap.get(factKeys.get(count++));
        }
    }
}
