import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day4A {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));

        int overlappingPairs = 0;
        while (reader.hasNextLine()) {
            String[] assignments = reader.nextLine().split("[,-]+", 0);

            int A1 = Integer.parseInt(assignments[0]);
            int A2 = Integer.parseInt(assignments[1]);
            int B1 = Integer.parseInt(assignments[2]);
            int B2 = Integer.parseInt(assignments[3]);

            if (A1 < B1) {
                if (A2 >= B2) {
                    overlappingPairs++;
                }
            } else if (A1 > B1) {
                if (A2 <= B2) {
                    overlappingPairs++;
                }
            } else if (A1 == B1) {
                overlappingPairs++;
            }
        }
        System.out.println(overlappingPairs);
    }
}
