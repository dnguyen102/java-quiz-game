package com.kenzie.app.Helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kenzie.app.CustomException.CustomEmptyStringException;
import com.sun.jdi.PrimitiveValue;

import java.util.Locale;

public class Result {
    private static int point = 0;

    //method to check if answer is correct
    public static boolean checkAnswer(String userAnswer, String url) throws JsonProcessingException {
        String webAnswer = MethodHelper.getAnswer(url);
        String correctAnswer = cleanAnswer(webAnswer);
        if (correctAnswer.toLowerCase().contains(userAnswer.toLowerCase())) {
            return true;
        }
        return false;
    }

    //method to remove any  whitespace trail leading or "-" in answer
    public static String cleanAnswer (String answer) {
        return answer.toLowerCase().replaceAll("[\\s+]|[\\-]","");
    }

    public static void displayAnsAndScore (boolean checkedAnswer, String url) throws JsonProcessingException {
        if (checkedAnswer) {
            point = point + 1;
            System.out.println("Your answer is correct!");
            System.out.println("Total score: " + point);
        } else {
            System.out.println("Your answer is not correct!");
            System.out.println("Correct answer: " + MethodHelper.getAnswer(url));
            System.out.println("Total score: " + point);
        }
    }

    public static void displayResult() {
        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println("Congratulation! You have finished the quiz");
        System.out.println("Total score: " + point);
    }
}
