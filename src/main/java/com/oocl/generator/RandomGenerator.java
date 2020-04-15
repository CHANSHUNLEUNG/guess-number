package com.oocl.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RandomGenerator implements NumberGenerator {

    public static final int NUMBER_RANGE = 10;
    public static final int ANSWER_LENGTH = 4;

    @Override
    public ArrayList<Integer> generateNumber() {
        Set<Integer> randomSet = new HashSet<>();
        while (randomSet.size() < ANSWER_LENGTH) {
            randomSet.add((int) (Math.random() * NUMBER_RANGE));
        }
        return new ArrayList<>(randomSet);
    }
}
