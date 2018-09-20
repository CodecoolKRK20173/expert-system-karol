package com.codecool.expertsystem;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;


public class RuleRepository{
    private Map<String, Question> questionMap;
    private Iterator<Question> questionIterator;

    RuleRepository(){
        questionMap = new LinkedHashMap<String, Question>();
        questionIterator = new QuestionIterator();
    }


    public void addQuestion(Question question){
        questionMap.put(question.getId(), question);
    }

    public Question getQuestionById(String id){
        return questionMap.get(id);
    }

    public Iterator<Question> getIterator(){
        return questionIterator;
    }



    private class QuestionIterator implements Iterator<Question>{

        int count;
        
        QuestionIterator(){
            count = 0;
        }


        @Override
        public boolean hasNext() {
            return (count < questionMap.size() && questionMap != null);
        }

        
        @Override
        public Question next() {
            List<String> questionKeys = new ArrayList<String>();

            for(Map.Entry entry : questionMap.entrySet()){
                questionKeys.add(entry.getKey().toString());
            }

            return questionMap.get(questionKeys.get(count++));
        }
    }
}
