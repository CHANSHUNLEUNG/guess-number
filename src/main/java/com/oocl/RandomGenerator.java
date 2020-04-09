package com.oocl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RandomGenerator implements NumberGenerator{
    @Override
    public ArrayList<Integer> generateNumber() {
        Set randomSet = new HashSet();
        while(randomSet.size() < GuessNumber.ANSWER_LENGTH){
            randomSet.add((int)(Math.random() * 10));
        }
        return new ArrayList<>(randomSet);
    }
}
