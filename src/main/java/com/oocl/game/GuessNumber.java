package com.oocl.game;

import com.oocl.generator.RandomGenerator;
import com.oocl.validator.GuessNumberValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GuessNumber {
    public static final int ANSWER_LENGTH = 4;
    private static final int USER_CHANCE_NUMBER = 6;
    private ArrayList<Integer> answerList;

    public GuessNumber(RandomGenerator randomGenerator) {
        answerList = new ArrayList<>();
        answerList = randomGenerator.generateNumber();
    }

    private ArrayList<Integer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Integer> answerList) {
        this.answerList = answerList;
    }

    public ArrayList<Integer> parseUserInput(String userInputLine) {
        ArrayList<Integer> resultList = new ArrayList<>();
        new ArrayList<>(Arrays.asList(userInputLine.split(" ")))
                .forEach(element -> resultList.add(Integer.parseInt(element)));
        return resultList;
    }

    public String feedback(ArrayList<Integer> userNumbers) {
        int correctNumberAndPositionCount = 0;
        int correctOnlyNumberCount = 0;
        for (int index = 0; index < userNumbers.size(); index++) {
            boolean isNumberAndPosition = getAnswerList().contains(userNumbers.get(index)) &&
                    getAnswerList().indexOf(userNumbers.get(index)) == index;
            boolean isOnlyNumber = getAnswerList().contains(userNumbers.get(index)) &&
                    getAnswerList().indexOf(userNumbers.get(index)) != index;
            if (isNumberAndPosition) {
                correctNumberAndPositionCount++;
            }
            if (isOnlyNumber) {
                correctOnlyNumberCount++;

            }
        }
        return correctNumberAndPositionCount + "A" + correctOnlyNumberCount + "B";
    }

    public static void main(String[] argv) {
        GuessNumber game = new GuessNumber(new RandomGenerator());

        Scanner userInputScanner = new Scanner(System.in);
        int userInputCount = 0;

        while (userInputScanner.hasNext()) {

            userInputCount++;

            String userInputLine = userInputScanner.nextLine();
            if (!new GuessNumberValidator().userInputCorrect(userInputLine)) {
                System.out.println("Wrong Input, Input Again");
                userInputCount--;
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
