package com.codecool.expertsystem;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        RuleParser ruleParser = new RuleParser();
        FactParser factParser = new FactParser();
        ESProvider esProvider = new ESProvider(factParser, ruleParser);
        esProvider.collectAnswers();
        System.out.println(esProvider.evaluate());

    }
}
