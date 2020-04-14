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
    public void should_validate_user_input() {
        assertTrue(guessNumberValidator.userInputCorrect("2 3 4 5"));
        assertFalse(guessNumberValidator.userInputCorrect("a 3 4 5"));
        assertFalse(guessNumberValidator.userInputCorrect("2 2 4 5"));
        assertFalse(guessNumberValidator.userInputCorrect("2 2 4 5 6"));
    }

    @Test
    public void should_parse_user_input() {
        ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(2, 3, 4, 5));
        assertEquals(expectedList, game.parseUserInput("2 3 4 5"));
    }

    @Test
    public void should_all_number_correct_and_all_position_correct() {
        assertEquals("4A0B", game.feedback(game.parseUserInput("1 2 3 4")));
    }

    @Test
    public void should_all_number_correct_and_partial_position_correct() {
        assertEquals("2A2B", game.feedback(game.parseUserInput("1 2 4 3")));
    }

    @Test
    public void should_partial_number_correct_and_partial_position_correct() {
        assertEquals("1A1B", game.feedback(game.parseUserInput("1 3 5 6")));
    }

    @Test
    public void should_all_number_correct_and_all_position_wrong() {
        assertEquals("0A4B", game.feedback(game.parseUserInput("4 3 2 1")));
    }

    @Test
    public void should_partial_number_correct_and_all_position_wrong() {
        assertEquals("0A2B", game.feedback(game.parseUserInput("4 3 5 6")));
    }

    @Test
    public void should_all_number_wrong_and_all_position_wrong() {
        assertEquals("0A0B", game.feedback(game.parseUserInput("5 6 7 8")));
    }
}
