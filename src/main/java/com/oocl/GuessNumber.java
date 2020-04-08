package com.oocl;

import java.util.*;

public class GuessNumber {
    private static final int ANSWER_LENGTH = 4;
    private ArrayList<Integer> answerList;

    public GuessNumber() {
        answerList = new ArrayList<>();
        generateNewAnswer();
    }

    public ArrayList<Integer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Integer> answerList) {
        this.answerList = answerList;
    }

    public void generateNewAnswer() {
        while (answerList.size() < ANSWER_LENGTH) {
            int tempRandomNumber = (int) (Math.random() * 10);
            if (!answerList.contains(tempRandomNumber)) {
                answerList.add(tempRandomNumber);
            }
        }
    }

    public boolean validateUserInput(String userInputLine) {
        Set<String> userInputSet = new HashSet<>(Arrays.asList(userInputLine.split(" ")));
        if (userInputLine.split(" ").length != 4) {
            return false;
        }
        if (userInputSet.size() != 4) {
            return false;
        }
        for (String userInputString : userInputSet) {
            try {
                int userInputInteger = Integer.parseInt(userInputString);
                if (userInputInteger < 0 || userInputInteger > 9) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> parseUserInput(String userInputLine) {
        ArrayList<Integer> resultList = new ArrayList<>();
        new ArrayList<String>(Arrays.asList(userInputLine.split(" ")))
                .stream().forEach(element -> resultList.add(Integer.parseInt(element)));
        return resultList;
    }

    public String play() {

        return "";
    }

    public String correctNumberAndPosition(ArrayList<Integer> userNumbers) {
        int count = 0;
        for (int index = 0; index < ANSWER_LENGTH; index++) {
            if(userNumbers.get(index) == getAnswerList().get(index)){
                count++;
            }
        }
        return String.valueOf(count);
    }
    public String correctNumberButWrongPosition(ArrayList<Integer> userNumbers) {
        int count = 0;
        for(int index = 0; index < ANSWER_LENGTH; index++){
            for(int answer : answerList){
                if(answer == userNumbers.get(index) && answerList.indexOf(answer) != index){
                    count++;
                }
            }
        }
        return String.valueOf(count);
    }

    public static void main(String argv[]) {
        GuessNumber game = new GuessNumber();
        game.getAnswerList().stream().forEach(System.out::println);
        Scanner userInputScanner = new Scanner(System.in);

        while (userInputScanner.hasNext()) {
            String userInputLine = userInputScanner.nextLine();
            System.out.println(game.validateUserInput(userInputLine));
        }

    }
}
