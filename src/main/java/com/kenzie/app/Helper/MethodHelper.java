package com.kenzie.app.Helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.CustomException.CustomEmptyStringException;
import com.kenzie.app.HTTPCilient.CustomHttpClient;
import com.kenzie.app.DTOClass.SpecificClueDTO;

import java.util.Random;

public class MethodHelper {
    //declare variable
    private static int id = 0;

    //declare methods
    //Method get random question from url
    public static String getRandQuestions(String url) throws JsonProcessingException {
        //objectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        //Declare DTO object
        SpecificClueDTO clueDTOObj;

        //initialize random
        Random random = new Random();

        //use random to get question
        id = random.nextInt(355237 - 1) + 1;

        String queryStr = url + "/" + id;
        String responseString = CustomHttpClient.sendGET(queryStr);

        //read value
        clueDTOObj = objectMapper.readValue(responseString, SpecificClueDTO.class);

        return clueDTOObj.getQuestion();
    }

    //method to get category from url
    public static String getCategory(String url) throws JsonProcessingException {
       //objectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        //Declare DTO object
        SpecificClueDTO clueDTOObj;

        String queryStr = url + "/" + id;
        String responseString = CustomHttpClient.sendGET(queryStr);

        //read value
        clueDTOObj = objectMapper.readValue(responseString, SpecificClueDTO.class);

        return clueDTOObj.getCategory().getTitle();
    }

    //method to get answer from url
    public static String getAnswer(String url) throws JsonProcessingException {
        //objectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        //Declare DTO object
        SpecificClueDTO clueDTOObj;

        String getQuest = url + "/" + id;
        String responseString = CustomHttpClient.sendGET(getQuest);

        //read value
        clueDTOObj = objectMapper.readValue(responseString, SpecificClueDTO.class);

        return clueDTOObj.getAnswer();
    }

    //method to check if empty String was entered
    public static void checkForEmptyStr(String input) {
        if (input.equals("")) {
            throw new CustomEmptyStringException("Invalid input: Empty string enter. ");
        }
    }

    //method convert string input to int input
    public static int convertStringInput(String input){
        return Integer.parseInt(input);
    }
}
