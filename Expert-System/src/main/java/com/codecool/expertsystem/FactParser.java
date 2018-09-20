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
import java.lang.Boolean;

import javax.xml.parsers.ParserConfigurationException;

public class FactParser extends XMLParser{

    FactRepository factRepository;

    FactParser(){
        factRepository = new FactRepository();
    }

    
    @Override
    public void loadXMLDocument(String xmlPath){
        try{
            File xmlFile = new File(xmlPath);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = builderFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
    
            doc.getDocumentElement().normalize();

            NodeList docList = doc.getElementsByTagName("Fact");
    
            for(int temp = 0; temp < docList.getLength(); temp++){
                Node node = docList.item(temp);
                Element element = (Element) node;
                NodeList descList = element.getElementsByTagName("Description");
                Node descriptionNode = descList.item(0);
                Element descriptionElement = (Element) descriptionNode;
                String factId = element.getAttribute("id");
                String descriptionValue = descriptionElement.getAttribute("value");


                NodeList evalsList = element.getElementsByTagName("Eval");
                Fact fact = new Fact(factId, descriptionValue);
                for (int j = 0; j < evalsList.getLength(); j++) {
                    Node evalNode = evalsList.item(j);
                    Element eval = (Element) evalNode;
                    String factEvalId = eval.getAttribute("id");
                    String factEvalValue = eval.getTextContent();
                    Boolean value = Boolean.valueOf(factEvalValue);
                    
                    fact.setFactValueById(factEvalId, value);
            }
                factRepository.addFact(fact);
                
            }
            
            
        }catch(ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
    }


    public FactRepository getFactRepository(){
        return factRepository;
    }
}
