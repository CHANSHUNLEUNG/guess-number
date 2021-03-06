package com.oocl.unit;

import com.oocl.game.GuessNumber;
import com.oocl.generator.RandomGenerator;
import com.oocl.validator.GuessNumberValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GuessNumberTest {
    private GuessNumber game;
    private GuessNumberValidator guessNumberValidator;

    @Before
    public void setUp() {
        guessNumberValidator = new GuessNumberValidator();
        RandomGenerator randomGenerator = Mockito.mock(RandomGenerator.class);
        Mockito.when(randomGenerator.generateNumber()).thenReturn(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
        game = new GuessNumber(randomGenerator);
        game.setAnswerList(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void should_pass_validator_when_user_input_completely_correct() {
        assertTrue(guessNumberValidator.isUserInputCorrect("2 3 4 5"));
    }

    @Test
    public void should_fail_validator_when_user_input_contain_character() {
        assertFalse(guessNumberValidator.isUserInputCorrect("a 3 4 5"));
    }

    @Test
    public void should_fail_validator_when_user_input_have_duplicate_number_with_length_4() {
        assertFalse(guessNumberValidator.isUserInputCorrect("2 2 4 5"));
    }

    @Test
    public void should_fail_validator_when_user_input_have_duplicate_number_with_length_5() {
        assertFalse(guessNumberValidator.isUserInputCorrect("2 2 4 5 6"));
    }

    @Test
    public void should_parse_user_input() {
        //Given
        ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(2, 3, 4, 5));

        assertEquals(expectedList, game.parseUserInput("2 3 4 5"));
    }

    @Test
    public void should_all_number_correct_and_all_position_correct() {
        //Given
        ArrayList<Integer> userNumbers = game.parseUserInput("1 2 3 4");
        //When
        String feedback = game.feedback(userNumbers);

        assertEquals("4A0B", feedback);
    }

    @Test
    public void should_all_number_correct_and_partial_position_correct() {
        //Given
        ArrayList<Integer> userNumbers = game.parseUserInput("1 2 4 3");
        //When
        String feedback = game.feedback(userNumbers);

        assertEquals("2A2B", feedback);
    }

    @Test
    public void should_partial_number_correct_and_partial_position_correct() {
        //Given
        ArrayList<Integer> userNumbers = game.parseUserInput("1 3 5 6");
        //When
        String feedback = game.feedback(userNumbers);

        assertEquals("1A1B", feedback);
    }

    @Test
    public void should_all_number_correct_and_all_position_wrong() {
        //Given
        ArrayList<Integer> userNumbers = game.parseUserInput("4 3 2 1");
        //When
        String feedback = game.feedback(userNumbers);

        assertEquals("0A4B", feedback);
    }

    @Test
    public void should_partial_number_correct_and_all_position_wrong() {
        //Given
        ArrayList<Integer> userNumbers = game.parseUserInput("4 3 5 6");
        //When
        String feedback = game.feedback(userNumbers);

        assertEquals("0A2B", feedback);
    }

    @Test
    public void should_all_number_wrong_and_all_position_wrong() {
        //Given
        ArrayList<Integer> userNumbers = game.parseUserInput("5 6 7 8");
        //When
        String feedback = game.feedback(userNumbers);

        assertEquals("0A0B", feedback);
    }
}
