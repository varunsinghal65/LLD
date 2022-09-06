package SnakeLadder;

import SnakeLadder.models.MoveRequest;
import SnakeLadder.services.DiceServiceImp;
import SnakeLadder.services.IDiceService;
import SnakeLadder.services.IMoveService;
import SnakeLadder.services.MoveServiceImpl;

import java.util.*;

public class SnakeLadderBoard {

    final static int NUMBER_OF_DICES = 1;
    final static int MAX_VALUE_PER_DICE = 6;

    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;
    private Map<String, Integer> playerPos;
    private IMoveService moveService;
    private IDiceService diceService;
    public int boardSize;

    public Map<Integer, Integer> getSnakes() {
        return Collections.unmodifiableMap(snakes);
    }

    public Map<Integer, Integer> getLadders() {
        return Collections.unmodifiableMap(ladders);
    }

    public SnakeLadderBoard(int boardSize, String[] playerNames, HashMap<Integer, Integer> snakes,
                            HashMap<Integer, Integer> ladders) {
        this.boardSize = boardSize;
        diceService = new DiceServiceImp();
        moveService = new MoveServiceImpl();
        this.snakes = snakes;
        this.ladders = ladders;
        playerPos = new LinkedHashMap<>();
        Arrays.stream(playerNames).forEach(playerName -> playerPos.put(playerName, 0));
    }

    public String moveAllPlayers() {
        for (String playerName : playerPos.keySet()) {
            //roll the dice
            Integer diceRollValue = diceService.getDiceRollValue(NUMBER_OF_DICES, MAX_VALUE_PER_DICE);
            //process the movement
            int start = playerPos.get(playerName);
            int end = start + diceRollValue > 100 ? start : start + diceRollValue;
            MoveRequest moveRequest = new MoveRequest.MoveRequestBuilder(start, end)
                    .withSnakes(snakes)
                    .withLadders(ladders)
                    .build();
            int newPos = moveService.move(moveRequest);
            playerPos.put(playerName, newPos);
            //detect winner, if detected, we return playername
            if (newPos == 100) {
                return playerName;
            }
        }
        return null;
    }
}
