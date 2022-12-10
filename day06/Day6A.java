import java.io.*;
import java.util.*;

public class Day6A {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));
        String data = reader.nextLine();
        String packet;
        boolean noRepeats = false;
        int N = 4;
        int index = N;

        checkChars: while (!noRepeats) {
            packet = data.substring(index - N, index);
            char[] letters = packet.toCharArray();
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (letters[i] == letters[j]) {
                        index++;
                        continue checkChars;
                    }
                }
            }
            noRepeats = true;
        }
        System.out.println(index);
    }
}
