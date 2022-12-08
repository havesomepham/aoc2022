import java.util.*;
import java.io.*;

public class Day8B {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));
        int N = 99; // known
        int[][] forest = new int[N][N];

        for (int i = 0; i < forest.length; i++) {
            String line = reader.nextLine();
            char[] lineChars = line.toCharArray();
            int[] lineInts = new int[N];
            for (int j = 0; j < lineChars.length; j++) {
                lineInts[j] = Character.getNumericValue(lineChars[j]);
            }
            forest[i] = lineInts;
        }

        int maxScenicScore = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (findScenicScore(i, j, forest, N) > maxScenicScore) {
                    maxScenicScore = findScenicScore(i, j, forest, N);
                }
            }
        }

        System.out.println(maxScenicScore);
    }

    private static int findScenicScore(int i, int j, int[][] forest, int N) {
        int height = forest[i][j];
        int score = 1;

        // if tree is on the edge
        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
            return 0;
        }

        for (int k = j + 1; k < N; k++) { // check all to the right
            if (forest[i][k] >= height) {
                score *= k - j;
                break;
            } else if (k == N - 1) {
                score *= k - j;
            }
        }

        for (int k = j - 1; k >= 0; k--) { // check all to the left
            if (forest[i][k] >= height) {
                score *= j - k;
                break;
            } else if (k == 0) {
                score *= j - k;
            }
        }

        for (int k = i + 1; k < N; k++) { // check all down
            if (forest[k][j] >= height) {
                score *= k - i;
                break;
            } else if (k == N - 1) {
                score *= k - i;
            }
        }

        for (int k = i - 1; k >= 0; k--) { // check all up
            if (forest[k][j] >= height) {
                score *= i - k;
                break;
            } else if (k == 0) {
                score *= i - k;
            }
        }
        return score;
    }
}
