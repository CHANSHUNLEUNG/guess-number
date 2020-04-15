package com.oocl.generator;

import com.oocl.game.GuessNumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RandomGenerator implements NumberGenerator {

    public static final int NUMBER_RANGE = 10;

    @Override
    public ArrayList<Integer> generateNumber() {
        Set<Integer> randomSet = new HashSet<>();
        while (randomSet.size() < GuessNumber.ANSWER_LENGTH) {
            randomSet.add((int) (Math.random() * NUMBER_RANGE));
        }
        return new ArrayList<>(randomSet);
    }
}
