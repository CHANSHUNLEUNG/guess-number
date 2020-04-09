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
    public void setUp() throws Exception {
        guessNumberValidator = new GuessNumberValidator();
        RandomGenerator randomGenerator = Mockito.mock(RandomGenerator.class);
        Mockito.when(randomGenerator.generateNumber()).thenReturn(new ArrayList<Integer>(Arrays.asList(1,2,3,4)));
        game = new GuessNumber(randomGenerator);
        game.setAnswerList(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void should_game_user_input_validate() {
        assertEquals(true, guessNumberValidator.userInputCorrect("2 3 4 5"));
        assertEquals(false, guessNumberValidator.userInputCorrect("a 3 4 5"));
        assertEquals(false, guessNumberValidator.userInputCorrect("2 2 4 5"));
        assertEquals(false, guessNumberValidator.userInputCorrect("2 2 4 5 6"));
    }

    @Test
    public void should_parse_user_input() {
        ArrayList<Integer> expectedList = new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5));
        assertEquals(expectedList, game.parseUserInput("2 3 4 5"));
    }

    @Test
    public void should_game_feedback() {
        assertEquals("1A0B", game.feedback(game.parseUserInput("1 5 6 7")));
        assertEquals("0A2B", game.feedback(game.parseUserInput("2 4 7 8")));
        assertEquals("1A2B", game.feedback(game.parseUserInput("0 3 2 4")));
        assertEquals("0A0B", game.feedback(game.parseUserInput("5 6 7 8")));
        assertEquals("0A4B", game.feedback(game.parseUserInput("4 3 2 1")));
        assertEquals("4A0B", game.feedback(game.parseUserInput("1 2 3 4")));
    }
}
