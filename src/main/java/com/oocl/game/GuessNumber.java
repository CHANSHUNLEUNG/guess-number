package com.oocl.game;

import com.oocl.generator.RandomGenerator;

import java.util.*;

public class GuessNumber {
    public static final int ANSWER_LENGTH = 4;
    public static final int USER_CHANCE_NUMBER = 6;
    private ArrayList<Integer> answerList;

    public GuessNumber(RandomGenerator randomGenerator) {
        answerList = new ArrayList<>();
        answerList = randomGenerator.generateNumber();
    }

    public ArrayList<Integer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Integer> answerList) {
        this.answerList = answerList;
    }

    public boolean validateUserInput(String userInputLine) {
        Set<String> userInputSet = new HashSet<>(Arrays.asList(userInputLine.split(" ")));
        if (userInputLine.split(" ").length != ANSWER_LENGTH || userInputSet.size() != ANSWER_LENGTH) {
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
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        new ArrayList<String>(Arrays.asList(userInputLine.split(" ")))
                .stream().forEach(element -> resultList.add(Integer.parseInt(element)));
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

    public static void main(String argv[]) {
        GuessNumber game = new GuessNumber(new RandomGenerator());
//        game.answerList.forEach(System.out::println);

        Scanner userInputScanner = new Scanner(System.in);
        int userInputCount = 0;

        while (userInputScanner.hasNext()) {

            userInputCount++;

            String userInputLine = userInputScanner.nextLine();
            if (!game.validateUserInput(userInputLine)) {
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
