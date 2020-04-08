package com.oocl;

import java.util.*;
import java.util.stream.IntStream;

public class GuessNumber {
    private static final int ANSWER_LENGTH = 4;
    private ArrayList<Integer> answer;

    public GuessNumber() {
        answer = new ArrayList<>();
        generateNewAnswer();
    }

    public ArrayList<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Integer> answer) {
        this.answer = answer;
    }

    public void generateNewAnswer() {
        while (answer.size() < ANSWER_LENGTH) {
            int tempRandomNumber = (int) (Math.random() * 10);
            if (!answer.contains(tempRandomNumber)) {
                answer.add(tempRandomNumber);
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
        for (int number = 0; number < ANSWER_LENGTH; number++) {
            if(userNumbers.get(number) == getAnswer().get(number)){
                count++;
            }
        }
        return String.valueOf(count);
    }

    public static void main(String argv[]) {
        GuessNumber game = new GuessNumber();
        game.getAnswer().stream().forEach(System.out::println);
        Scanner userInputScanner = new Scanner(System.in);

        while (userInputScanner.hasNext()) {
            String userInputLine = userInputScanner.nextLine();
            System.out.println(game.validateUserInput(userInputLine));
        }

    }
}
