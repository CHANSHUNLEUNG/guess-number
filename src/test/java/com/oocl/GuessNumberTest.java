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
    public void should_game_feedback() {
//        assertEquals(game.play());
    }
}
