package com.oocl.validator;

import com.oocl.exception.UserInputIncorrectException;
import com.oocl.generator.RandomGenerator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GuessNumberValidator implements UserInputValidator {

    public static final String USER_INPUT_DELIMITER = " ";
    public static final int USER_INPUT_START = 0;
    public static final int USER_INPUT_END = 9;

    @Override
    public boolean userInputCorrect(String userInputLine) {
        Set<String> userInputSet = new HashSet<>(Arrays.asList(userInputLine.split(USER_INPUT_DELIMITER)));
        if (userInputLine.split(" ").length != RandomGenerator.ANSWER_LENGTH ||
                userInputSet.size() != RandomGenerator.ANSWER_LENGTH) {
            return false;
        }
        for (String userInputString : userInputSet) {
            try {
                int userInputInteger = Integer.parseInt(userInputString);
                if (userInputInteger < USER_INPUT_START || userInputInteger > USER_INPUT_END) {
                    throw new UserInputIncorrectException(UserInputIncorrectException.USER_INPUT_ERROR);
                }
            } catch (UserInputIncorrectException | NumberFormatException userInputIncorrectException) {
                System.out.println(userInputIncorrectException.getMessage());
                return false;
            }
        }
        return true;
    }
}
