package SnakeLadder.models;

import java.util.HashMap;

public class MoveRequest {
    private int start;
    private int end;
    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;

    private MoveRequest(int start, int end, HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders) {
        this.start = start;
        this.end = end;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public HashMap<Integer, Integer> getSnakes() {
        return snakes;
    }

    public HashMap<Integer, Integer> getLadders() {
        return ladders;
    }

    public static class MoveRequestBuilder {
        private HashMap<Integer, Integer> snakes;
        private HashMap<Integer, Integer> ladders;
        private int start;
        private int end;

        public MoveRequestBuilder withSnakes(HashMap<Integer, Integer> snakes) {
            this.snakes = snakes;
            return this;
        }

        public MoveRequestBuilder withLadders(HashMap<Integer, Integer> ladders) {
            this.ladders = ladders;
            return this;
        }

        public MoveRequestBuilder(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public MoveRequest build() {
            return new MoveRequest(start, end, snakes, ladders);
        }
    }
}

