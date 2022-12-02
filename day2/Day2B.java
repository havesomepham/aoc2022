import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Day2B {
    public static void main(String[] args) throws IOException {
        Scanner keyIn = new Scanner(System.in);
        File inFile = new File("day2/input.txt");
        Scanner fileIn = new Scanner(inFile);

        int points = 0;
        while (fileIn.hasNextLine()) {
            String opponentChoice = fileIn.next();
            String playerChoice = fileIn.next();
            points += decideResult(opponentChoice, playerChoice);
        }

        System.out.println(points);
        keyIn.close();
        fileIn.close();
    }

    private static int decideResult(String opponentChoice, String playerChoice) {
        switch (opponentChoice) {
            case "A": // Rock
                switch (playerChoice) {
                    case "X": // Lose
                        return 3 + 0;
                    case "Y": // Draw
                        return 1 + 3;
                    case "Z": // Win
                        return 2 + 6;
                }
            case "B": // Paper
                switch (playerChoice) {
                    case "X": // Lose
                        return 1 + 0;
                    case "Y": // Draw
                        return 2 + 3;
                    case "Z": // Win
                        return 3 + 6;
                }
            case "C": // Scissors
                switch (playerChoice) {
                    case "X": // Lose
                        return 2 + 0;
                    case "Y": // Draw
                        return 3 + 3;
                    case "Z": // Win
                        return 1 + 6;
                }
        }
        return 0;
    }
}
