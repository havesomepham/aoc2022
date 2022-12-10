import java.io.*;
import java.util.*;

public class Day9A {

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));

        int[] head = new int[2];
        int[] tail = new int[2];

        ArrayList<int[]> tailPositions = new ArrayList<>();
        tailPositions.add(Arrays.copyOf(tail, 2));

        while (reader.hasNextLine()) {
            String direction = reader.next();
            int distance = Integer.parseInt(reader.next());

            switch (direction) {
                case "R":
                    for (int i = 0; i < distance; i++) {
                        head[0]++;
                        if (head[0] - tail[0] > 1) {
                            tail[0] = head[0] - 1;
                            tail[1] = head[1];
                            registerTailPosition(tail, tailPositions);
                        }
                    }
                    break;
                case "L":
                    for (int i = 0; i < distance; i++) {
                        head[0]--;
                        if (head[0] - tail[0] < -1) {
                            tail[0] = head[0] + 1;
                            tail[1] = head[1];
                            registerTailPosition(tail, tailPositions);
                        }
                    }
                    break;
                case "U":
                    for (int i = 0; i < distance; i++) {
                        head[1]++;
                        if (head[1] - tail[1] > 1) {
                            tail[1] = head[1] - 1;
                            tail[0] = head[0];
                            registerTailPosition(tail, tailPositions);
                        }
                    }
                    break;
                case "D":
                    for (int i = 0; i < distance; i++) {
                        head[1]--;
                        if (head[1] - tail[1] < -1) {
                            tail[1] = head[1] + 1;
                            tail[0] = head[0];
                            registerTailPosition(tail, tailPositions);
                        }
                    }
                    break;
            }
        }
        System.out.println(tailPositions.size());
    }

    private static void registerTailPosition(int[] tail, ArrayList<int[]> tailPositions) {
        for (int i = 0; i < tailPositions.size(); i++) {
            // if tail position is already in the list
            if (tailPositions.get(i)[0] == tail[0] && tailPositions.get(i)[1] == tail[1]) {
                return;
            }
        }
        tailPositions.add(Arrays.copyOf(tail, 2));
    }
}
