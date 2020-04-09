package com.oocl;

import java.util.*;

public class GuessNumber {
    private static final int ANSWER_LENGTH = 4;
    private static final int USER_CHANCE_NUMBER = 6;
    private ArrayList<Integer> answerList;

    public GuessNumber() {
        answerList = new ArrayList<>();
        generateNewAnswer();
        //debug
//        setAnswerList(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)));
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

    public String feedback(ArrayList<Integer> userNumbers) {
        return correctNumberAndPosition(userNumbers) + "A" + correctNumberButWrongPosition(userNumbers) + "B";
    }

    public String correctNumberAndPosition(ArrayList<Integer> userNumbers) {
        int count = 0;
        for (int index = 0; index < ANSWER_LENGTH; index++) {
            if (userNumbers.get(index) == getAnswerList().get(index)) {
                count++;
            }
        }
        return String.valueOf(count);
    }

    public String correctNumberButWrongPosition(ArrayList<Integer> userNumbers) {
        int count = 0;
        for (int index = 0; index < ANSWER_LENGTH; index++) {
            for (int answer : answerList) {
                if (answer == userNumbers.get(index) && answerList.indexOf(answer) != index) {
                    count++;
                }
            }
        }
        return String.valueOf(count);
    }

    public static void main(String argv[]) {
        GuessNumber game = new GuessNumber();
        game.answerList.forEach(System.out::println);
        Scanner userInputScanner = new Scanner(System.in);
        int userInputCount = 0;
        while (userInputScanner.hasNext()) {
            userInputCount++;
            String userInputLine = userInputScanner.nextLine();
            if (!game.validateUserInput(userInputLine)) {
                System.out.println("Wrong Input, Input Again");
                continue;
            } else {
                ArrayList<Integer> userNumbers = game.parseUserInput(userInputLine);
                System.out.println(game.feedback(userNumbers));
                if (game.feedback(userNumbers).equals("4A0B")) {
                    System.out.println("Congratulations! You win!");
                    break;
                }
            }
            if (userInputCount == USER_CHANCE_NUMBER) {
                System.out.println("Sorry, 6 chances have been used. You failed");
                break;
            }
        }

    }
}
