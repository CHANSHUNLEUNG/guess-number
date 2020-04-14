package com.oocl.validator;

import com.oocl.exception.UserInputIncorrectException;
import com.oocl.game.GuessNumber;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GuessNumberValidator implements UserInputValidator {
    @Override
    public boolean userInputCorrect(String userInputLine) {
        Set<String> userInputSet = new HashSet<>(Arrays.asList(userInputLine.split(" ")));
        if (userInputLine.split(" ").length != GuessNumber.ANSWER_LENGTH ||
                userInputSet.size() != GuessNumber.ANSWER_LENGTH) {
            return false;
        }
        for (String userInputString : userInputSet) {
            try {
                int userInputInteger = Integer.parseInt(userInputString);
                if (userInputInteger < 0 || userInputInteger > 9) {
                    throw new UserInputIncorrectException("user input should between 0-9");
                }
            } catch (UserInputIncorrectException | NumberFormatException userInputIncorrectException) {
                System.out.println(userInputIncorrectException.getMessage());
                return false;
            }
        }
        return true;
    }
}
