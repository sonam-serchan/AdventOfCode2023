package Day2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final int MAX_RED_BALLS = 12;
    private static final int MAX_GREEN_BALLS = 13;
    private static final int MAX_BLUE_BALLS = 14;

    public static void main (String[] args) throws IOException {
        String fileName = "src/day2/puzzle-input.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File does not exist: "+ fileName);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            processGameFile(br);
        } catch(IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void processGameFile(BufferedReader br) throws IOException {
        int validGameIdsSum = 0;
        int powerSum = 0;
        String line;

        while((line = br.readLine()) != null) {
            GameResult gameResult = processGameLine(line);
            if (gameResult.isValidGame)  {
                validGameIdsSum += gameResult.gameId;
            }
            powerSum += gameResult.power;
        }
        System.out.println("The game ids sum is " + validGameIdsSum + " and the sum of powers is " + powerSum);
    }

    private static GameResult processGameLine(String line) {
        String[] record = line.split(": ");
        int gameId = convertToNumber(record[0].split(" ")[1]);
        String[] sets = record[1].split("; ");

        Map<String, Integer> ballScores = calculateBallScores(sets);
        boolean isValidGame = isValidGame(ballScores);
        int power = ballScores.get("red") * ballScores.get("green") * ballScores.get("blue");

        return new GameResult(gameId, isValidGame, power);
    }

    private static Map<String, Integer> calculateBallScores(String[] sets) {
        Map<String, Integer> ballScores = new HashMap<>();
        for (String set: sets) {
            String[] balls = set.split(", ");
            for (String ball: balls) {
                updateBallScores(ballScores, ball);
            }
        }
        return ballScores;
    }

    private static void updateBallScores(Map<String, Integer> ballScores, String ball) {
        String[] ballSets = ball.split(" ");
        String ballName = ballSets[1];
        int ballScore = convertToNumber(ballSets[0]);

        ballScores.merge(ballName, ballScore, Integer::max);
    }

    private static boolean isValidGame(Map<String, Integer> ballScores) {
        return ballScores.getOrDefault("red", 0) <= MAX_RED_BALLS &&
                ballScores.getOrDefault("green", 0) <= MAX_GREEN_BALLS &&
                ballScores.getOrDefault("blue", 0) <= MAX_BLUE_BALLS;
    }

    public static int convertToNumber(String str) {
        return Integer.parseInt(str);
    }

    private record GameResult(int gameId, boolean isValidGame, int power) {
    }
}
