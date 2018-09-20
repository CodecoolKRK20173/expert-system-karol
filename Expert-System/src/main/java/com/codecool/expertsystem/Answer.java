package com.codecool.expertsystem;

import java.util.ArrayList;
import java.util.List;
public class Answer{
    private List<Value> values;

    Answer(){
        values = new ArrayList<Value>();
    }


    public boolean evaluateAnswerByInput(String input){
        for(Value value : values){
            if(value.getInputPattern().contains(input)){
                return value.getSelectionType();
            }
        }
        return false;
    }


    public void addValue(Value value){
        values.add(value);
    }

}
