package SnakeLadder.services;

import java.util.Random;

public class DiceServiceImp implements IDiceService{

    private Random random = new Random();
    @Override
    public int getDiceRollValue(final int numDices, final int maxValuesInDice) {
        return numDices + random.nextInt(maxValuesInDice*numDices-numDices+1);
    }
}
