package com.codecool.expertsystem;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.text.html.HTMLEditorKit.Parser;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class RuleParser extends XMLParser{
    RuleRepository ruleRepository;

    RuleParser(){
        ruleRepository = new RuleRepository();
    }

    @Override
    public void loadXMLDocument(String xmlPath){
        
        try{
            File xmlFile = new File(xmlPath);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = builderFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
    
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Rule");
    
            for(int temp = 0; temp < nList.getLength(); temp++){
                Node node = nList.item(temp);
                Element element = (Element) node;
                NodeList questionList = element.getElementsByTagName("Question");
                Node questionNode = questionList.item(0);
                Element questionElement = (Element) questionNode;
                String id = element.getAttribute("id");
                String questionText = questionElement.getTextContent();
                Answer answer = new Answer();


                NodeList selectionList = element.getElementsByTagName("Selection");

                for(int i = 0; i < selectionList.getLength();i++){
                    Node selectionNode = selectionList.item(i);
                    Element selectionElement = (Element) selectionNode;
                    String attribute = selectionElement.getAttribute("value");
                    Boolean attributeToBoolean = Boolean.valueOf(attribute);

                    NodeList singleValuesList = selectionElement.getElementsByTagName("SingleValue");
                    Node singleValueNode = singleValuesList.item(0);
                    Element singleValueElement = (Element) singleValueNode;
                    String singleValueString = singleValueElement.getAttribute("value");
                    Value singleValue = new SingleValue(singleValueString, attributeToBoolean);
                    answer.addValue(singleValue);

                }
                Question question = new Question(id, questionText, answer); //na koniec
                ruleRepository.addQuestion(question);   
            
            }
        }catch(ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }


        /*The RuleParser adds the questions with their corresponding id to the RuleRepository
         instance. This is stored in a Map inside the RuleRepository (as a field instance).
          The RuleRepository constructor initializes the QuestionIterator inner class that 
          implements the Iterator interface.
           With this we can iterate through the questions later.*/
    }

    public RuleRepository getRuleRepository(){
        return ruleRepository;
    }
}
