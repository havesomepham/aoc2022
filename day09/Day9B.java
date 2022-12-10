import java.io.*;
import java.util.*;

public class Day9B {

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));

        int[][] knots = new int[10][2];

        ArrayList<int[]> tailPositions = new ArrayList<>();
        tailPositions.add(Arrays.copyOf(knots[9], 2));

        while (reader.hasNextLine()) {
            String direction = reader.next();
            int distance = Integer.parseInt(reader.next());

            switch (direction) {
                case "R":
                    for (int i = 0; i < distance; i++) {
                        knots[0][0]++;
                        moveKnot(knots, 0, 1, tailPositions);
                    }
                    break;
                case "L":
                    for (int i = 0; i < distance; i++) {
                        knots[0][0]--;
                        moveKnot(knots, 0, 1, tailPositions);
                    }
                    break;
                case "U":
                    for (int i = 0; i < distance; i++) {
                        knots[0][1]++;
                        moveKnot(knots, 0, 1, tailPositions);
                    }
                    break;
                case "D":
                    for (int i = 0; i < distance; i++) {
                        knots[0][1]--;
                        moveKnot(knots, 0, 1, tailPositions);
                    }
                    break;
            }
        }
        System.out.println(tailPositions.size());
    }

    private static void moveKnot(int[][] knots, int front, int back,
            ArrayList<int[]> tailPositions) {

        if (front == knots.length - 1) {
            registerTailPosition(knots[front], tailPositions);
            return;
        }

        // if the knots are close enough, do nothing
        if (Math.abs(knots[front][0] - knots[back][0]) <= 1
                && Math.abs(knots[front][1] - knots[back][1]) <= 1) {
            return;
        }

        // straight line jumps
        if (knots[front][0] == knots[back][0]) {
            if (knots[front][1] > knots[back][1]) {
                knots[back][1] = knots[front][1] - 1;
            } else {
                knots[back][1] = knots[front][1] + 1;
            }
        } else if (knots[front][1] == knots[back][1]) {
            if (knots[front][0] > knots[back][0]) {
                knots[back][0] = knots[front][0] - 1;

            } else {
                knots[back][0] = knots[front][0] + 1;
            }
        }

        // diagonal jumps
        if (knots[front][0] - knots[back][0] > 1 && knots[front][1] - knots[back][1] > 1) {
            knots[back][0] = knots[front][0] - 1;
            knots[back][1] = knots[front][1] - 1;
        } else if (knots[front][0] - knots[back][0] < -1 && knots[front][1] - knots[back][1] > 1) {
            knots[back][0] = knots[front][0] + 1;
            knots[back][1] = knots[front][1] - 1;
        } else if (knots[front][0] - knots[back][0] > 1 && knots[front][1] - knots[back][1] < -1) {
            knots[back][0] = knots[front][0] - 1;
            knots[back][1] = knots[front][1] + 1;
        } else if (knots[front][0] - knots[back][0] < -1 && knots[front][1] - knots[back][1] < -1) {
            knots[back][0] = knots[front][0] + 1;
            knots[back][1] = knots[front][1] + 1;
        }

        // knights-move jumps
        if (knots[front][0] - knots[back][0] > 1) {
            knots[back][1] = knots[front][1];
            knots[back][0] = knots[front][0] - 1;
        } else if (knots[front][0] - knots[back][0] < -1) {
            knots[back][1] = knots[front][1];
            knots[back][0] = knots[front][0] + 1;
        } else if (knots[front][1] - knots[back][1] > 1) {
            knots[back][0] = knots[front][0];
            knots[back][1] = knots[front][1] - 1;
        } else if (knots[front][1] - knots[back][1] < -1) {
            knots[back][0] = knots[front][0];
            knots[back][1] = knots[front][1] + 1;
        }
        moveKnot(knots, front + 1, back + 1, tailPositions);
        return;
    }

    private static void registerTailPosition(int[] tail, ArrayList<int[]> tailPositions) {
        for (int i = 0; i < tailPositions.size(); i++) {
            if (tailPositions.get(i)[0] == tail[0] && tailPositions.get(i)[1] == tail[1]) {
                return;
            }
        }
        tailPositions.add(Arrays.copyOf(tail, 2));
    }
}
