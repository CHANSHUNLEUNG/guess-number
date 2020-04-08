package com.oocl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuessNumberTest {
    private GuessNumber game;
    @Before
    public void setUp() throws Exception {
        game = new GuessNumber();
    }

    @Test
    public void should_game_user_input_validate() {
        assertEquals(true, game.validateUserInput("2 3 4 5"));
        assertEquals(false, game.validateUserInput("a 3 4 5"));
        assertEquals(false, game.validateUserInput("2 2 4 5"));
        assertEquals(false, game.validateUserInput("2 2 4 5 6"));
    }
}
