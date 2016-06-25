package com.local.mez.dictionarydash;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;
import java.util.Stack;

public class ShortestWordTransformationApp {

    public static void main(String args[]) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ShortestWordTransformationConfiguration.class);

        ShortestWordTransformationConfiguration processor = context.getBean(ShortestWordTransformationConfiguration.class);
        Scanner scanner = new Scanner(System.in);

        processor.loadDictionary();

        System.out.print("Enter first word: ");
        String startWord = scanner.next();

        System.out.print("Enter second word: ");
        String endWord = scanner.next();

        Stack<String> result = processor.getShortestWordTransformation(startWord, endWord);

        if( !result.isEmpty() ) {
            System.out.println("shortest transformation is of length " + (result.size() - 1));
            System.out.println(result.toString());
        }
        else {
            System.out.println("no valid transformation found");
        }

    }
}
