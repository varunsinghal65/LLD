package SnakeLadder;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Client {

    /**
     *
     * @see https://workat.tech/machine-coding/editorial/how-to-design-snake-and-ladder-machine-coding-ehskk9c40x2w
     * Input to the program
     * ---------------------------
     * players varun,alpha,bravo
     * snakes 34-2,10-1,99-0,75-14,89-23
     * ladders 2-15,3-98,19-90,45-77
     * start
     * @param args
     */
    public static void main(String[] args) {
        String[] playerNames = null;
        HashMap<Integer, Integer> snakes = new HashMap<>();
        HashMap<Integer, Integer> ladders = new HashMap<>();
        while (true) {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String[] commands = scanner.nextLine().split(" ");
            String action = commands[0].toLowerCase();
            switch (action) {
                case "snakes":
                    String[] snakesStrings = commands[1].split(",");
                    Arrays.stream(snakesStrings).forEach(str->{
                        String[] headTail = str.split("-");
                        int head = Integer.valueOf(headTail[0]);
                        int tail = Integer.valueOf(headTail[1]);
                        snakes.put(head, tail);
                    });
                    break;
                case "ladders":
                    String[] laddersStrings =commands[1].split(",");
                    Arrays.stream(laddersStrings).forEach(str->{
                        String[] startEnd = str.split("-");
                        int start = Integer.valueOf(startEnd[0]);
                        int end = Integer.valueOf(startEnd[1]);
                        ladders.put(start, end);
                    });
                    break;
                case "players":
                    playerNames = commands[1].split(",");
                    break;
                case "start":
                    SnakeLadderBoard board = new SnakeLadderBoard(100, playerNames, snakes, ladders);
                    String winnerName = null;
                    while(winnerName == null || winnerName.isEmpty()) {
                        winnerName = board.moveAllPlayers();
                    }
                    System.out.println("The winner is: " + winnerName);
                    break;
                default: System.out.println("You have entered an unknown command.");

            }
        }

    }

}
