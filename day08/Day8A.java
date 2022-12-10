import java.util.*;
import java.io.*;

public class Day8A {
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

        int numberOfVisibleTrees = 0;
        // -1: not visible, 1: visible, 0: unknown
        int[][] visibilities = new int[N][N];
        for (int i = 0; i < N; i++) {
            int[] temp = new int[N];
            for (int j = 0; j < N; j++) {
                temp[j] = 0;
            }
            visibilities[i] = temp;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (checkVisiblity(i, j, visibilities, forest, N) == 1) {
                    numberOfVisibleTrees++;
                }
            }
        }

        System.out.println(numberOfVisibleTrees);
    }

    private static int checkVisiblity(int i, int j, int[][] visibilities, int[][] forest, int N) {
        int height = forest[i][j];

        // if tree has already been checked
        if (visibilities[i][j] != 0) {
            return visibilities[i][j];
        }

        // if tree is on the edge
        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
            return visibilities[i][j] = 1;
        }

        for (int k = j + 1; k < N; k++) { // check all to the right
            if (forest[i][k] >= height) {
                break;
            } else if (k == N - 1) {
                return visibilities[i][j] = 1;
            }
        }

        for (int k = j - 1; k >= 0; k--) { // check all to the left
            if (forest[i][k] >= height) {
                break;
            } else if (k == 0) {
                return visibilities[i][j] = 1;
            }
        }

        for (int k = i + 1; k < N; k++) { // check all down
            if (forest[k][j] >= height) {
                break;
            } else if (k == N - 1) {
                return visibilities[i][j] = 1;
            }
        }

        for (int k = i - 1; k >= 0; k--) { // check all up
            if (forest[k][j] >= height) {
                break;
            } else if (k == 0) {
                return visibilities[i][j] = 1;
            }
        }

        return visibilities[i][j] = -1;
    }
}
