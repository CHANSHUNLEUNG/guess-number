package com.oocl;

import java.util.ArrayList;

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

    public static void main(String argv[]) {
        GuessNumber game = new GuessNumber();
        game.getAnswer().stream().forEach(System.out::println);
    }
}
