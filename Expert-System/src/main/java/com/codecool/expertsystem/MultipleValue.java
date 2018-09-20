package com.codecool.expertsystem;

import java.util.ArrayList;
import java.util.List;

public class MultipleValue extends Value{
    private boolean selectionType;
    List<String> inputPattern;

    MultipleValue(List<String> param, Boolean selectionType){
        this.inputPattern = param;
        this.selectionType = selectionType;
    }


    @Override
    public List<String> getInputPattern() {
        return inputPattern;
    }

    @Override
    public boolean getSelectionType() {
        return selectionType;
    }
}
