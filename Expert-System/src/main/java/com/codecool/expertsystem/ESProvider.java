package com.codecool.expertsystem;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;

public class ESProvider{
    private FactParser factParser;
    private RuleParser ruleParser;
    private Map<String, Boolean> userAnswers;
    private Scanner scanner;

    public ESProvider(FactParser factParser, RuleParser ruleParser){
        this.factParser = factParser;
        this.ruleParser = ruleParser;
        factParser.loadXMLDocument("/home/karol/codecool/Java/Java - self learning/javasmth/javasl/expert-system-karol/Expert-System/src/main/java/com/codecool/expertsystem/Facts.xml");
        ruleParser.loadXMLDocument("/home/karol/codecool/Java/Java - self learning/javasmth/javasl/expert-system-karol/Expert-System/src/main/java/com/codecool/expertsystem/Rules.xml");

        scanner = new Scanner(System.in);
    }


    public void collectAnswers(){
        Iterator<Question> iterator = ruleParser.getRuleRepository().getIterator();
        userAnswers = new HashMap<String, Boolean>();

        while(iterator.hasNext()){
            Question question = iterator.next();
            String questionToPrint = question.getQuestion();
            System.out.println(questionToPrint);
            String userInput = scanner.nextLine();
            Boolean isValid = getAnswerByQuestion(question.getId(), userInput);
            userAnswers.put(question.getId(), isValid);
        }
    }


    public boolean getAnswerByQuestion(String questionId, String userInput){
        return ruleParser.getRuleRepository().getQuestionById(questionId).getEvaluatedAnswer(userInput);
    }

    
    public String evaluate(){
        Iterator<Fact> factIterator = factParser.getFactRepository().getIterator();
        String result;
        while(factIterator.hasNext()){
            Fact factToCheck = factIterator.next();
            if(checkFact(factToCheck)){
                result = "You should buy a " + factToCheck.getDescription();
                return result;
            }

        }
        return "Something is wrong, sorry!";
        
    }

    private boolean checkFact(Fact fact){
        Set<String> factIdSet = fact.getIdSet();

        for(String id : factIdSet){
            if(!this.userAnswers.get(id).equals(fact.getValueById(id))){
                System.out.println("false");
                return false;
            }
        }
        System.out.println("True");
        return true;

    }
}
