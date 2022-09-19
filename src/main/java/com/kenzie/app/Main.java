package com.kenzie.app;

// import necessary libraries


import com.fasterxml.jackson.core.JsonProcessingException;
import com.kenzie.app.CustomException.CustomEmptyStringException;
import com.kenzie.app.Helper.MethodHelper;
import com.kenzie.app.Helper.Result;

import java.util.Scanner;

public class Main {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!
     */
    private static final String URL = "https://jservice.kenzie.academy/api/clues";

    public static void main(String[] args) {
        //Write main execution code here
        //declare variable
        try {
            String answer;
            String choice;
            int numberInput = 0;
            boolean exitFlag = true;
            Scanner sc = new Scanner(System.in);

            //read in user input
            while (exitFlag) {
                try {
                    displayMenu();
                    choice = sc.nextLine();

                    //clean answer to remove spaces
                    String cleanAns = Result.cleanAnswer(choice);
                    numberInput = MethodHelper.convertStringInput(cleanAns);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: please put in number");
                }
                //throw custom exception if user enter blank space
                catch (CustomEmptyStringException e) {
                    System.out.println(e.getMessage() + " Please try again");
                }

                //exit loop
                if (numberInput > 0) {
                    exitFlag = false;
                }
            }

            //display question after user choice
            displayQuestion(numberInput);
        }
        catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error occur. Exiting... " + e.getMessage());
        }
    }

    public static void displayMenu() {
        System.out.println();
        System.out.println("---TRIVIA QUIZ---");
        System.out.println("1. Start quiz");
        System.out.println("2. Exit");
        System.out.println("Enter your choice (1 or 2): ");
        System.out.print("> ");
    }

    public static void displayQuestion(int number) throws JsonProcessingException {
        Scanner sc = new Scanner(System.in);
        String answer;
        int i = 1;
        boolean check;
        String ans = "";

        switch (number) {
            case 1 -> {
                System.out.println();
                System.out.println(" Answer all questions to see your score");

                //loop to ask 10 questions
                while (i <= 10) {
                    try {
                        System.out.println();
                        System.out.println(i + ". Question: " + MethodHelper.getRandQuestions(URL));
                        System.out.println("Category: " + MethodHelper.getCategory(URL));
                        System.out.println("Type \"exit\" to exit the quiz");
                        System.out.print("Your answer: ");
                        answer = sc.nextLine();

                        //remove any space in answer before compare to correct answer
                        ans = Result.cleanAnswer(answer);

                        //check if user enter blank space
                        MethodHelper.checkForEmptyStr(ans);

                        //check if answer is correct
                        check = Result.checkAnswer(ans, URL);

                        //option to exit quiz
                        if (ans.equalsIgnoreCase("exit")) {
                            break;
                        }

                        //display answer after checking
                        Result.displayAnsAndScore(check, URL);
                        i++;    //update question number

                    } catch (CustomEmptyStringException e) {
                        System.out.println(e.getMessage() + "Please try again");
                    }
                }
                //display result
                Result.displayResult();
            }
            case 2 -> {
                System.out.println();
                System.out.println("Goodbye!");
            }
            default -> System.out.println("Input not recognized. Exiting... Number must be 1 or 2");
        }
    }
}
