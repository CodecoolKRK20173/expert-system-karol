package com.codecool.expertsystem;

import java.util.ArrayList;
import java.util.List;


public class SingleValue extends Value{
    private boolean selectionType;
    private ArrayList<String> inputPattern;

    SingleValue(String param, Boolean selectionType){
        this.inputPattern = new ArrayList<String>();
        this.inputPattern.add(param);
        this.selectionType = selectionType;
    }


    @Override
    public ArrayList<String> getInputPattern() {
        return inputPattern;
    }

    @Override
    public boolean getSelectionType() {
        return selectionType;
    }
}
