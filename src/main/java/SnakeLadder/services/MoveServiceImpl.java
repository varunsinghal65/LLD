package SnakeLadder.services;

import SnakeLadder.models.MoveRequest;

import java.util.HashMap;

public class MoveServiceImpl implements IMoveService {
    @Override
    public int move(MoveRequest moveRequest) {
        int end = moveRequest.getEnd();
        int computedPos = end;
        HashMap<Integer, Integer> snakes = moveRequest.getSnakes();
        HashMap<Integer, Integer> ladders =  moveRequest.getLadders();
        while(snakes.containsKey(computedPos) || ladders.containsKey(computedPos)) {
            while(snakes.containsKey(computedPos)) {
                computedPos = snakes.get(computedPos);
            }
            while (ladders.containsKey(computedPos)) {
                computedPos = ladders.get(computedPos);
            }
        }
        return computedPos;
    }
}
