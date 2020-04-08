package com.oocl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GuessNumberTest {
    private GuessNumber game;
    @Before
    public void setUp() throws Exception {
        game = new GuessNumber();
        game.setAnswer(new ArrayList<Integer>(Arrays.asList(1,2,3,4)));
    }

    @Test
    public void should_game_user_input_validate() {
        assertEquals(true, game.validateUserInput("2 3 4 5"));
        assertEquals(false, game.validateUserInput("a 3 4 5"));
        assertEquals(false, game.validateUserInput("2 2 4 5"));
        assertEquals(false, game.validateUserInput("2 2 4 5 6"));
    }

    @Test
    public void should_parse_user_input() {
        ArrayList<Integer> expectedList = new ArrayList<Integer>(Arrays.asList(2,3,4,5));
        assertEquals(expectedList,game.parseUserInput("2 3 4 5"));
    }

    @Test
    public void should_correct_number_and_position() {
        assertEquals("1",game.correctNumberAndPosition(game.parseUserInput("1 5 6 7")));
        assertEquals("0",game.correctNumberAndPosition(game.parseUserInput("2 4 7 8")));
        assertEquals("1",game.correctNumberAndPosition(game.parseUserInput("0 3 2 4")));
        assertEquals("0",game.correctNumberAndPosition(game.parseUserInput("5 6 7 8")));
        assertEquals("0",game.correctNumberAndPosition(game.parseUserInput("4 3 2 1")));
        assertEquals("4",game.correctNumberAndPosition(game.parseUserInput("1 2 3 4")));
    }

    @Test
    public void should_game_feedback() {
//        assertEquals(game.play());
    }
}
