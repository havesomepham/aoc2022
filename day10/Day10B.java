import java.util.*;
import java.io.*;

public class Day10B {

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));
        CPUB curr = new CPUB();
        StringBuilder line = new StringBuilder();

        while (reader.hasNextLine()) {
            String command = reader.next();
            if (command.equals("noop")) {
                line.append(curr.noop());
            } else {
                int nextInt = reader.nextInt();
                line.append(curr.addx(nextInt));
                line.append(curr.addx(nextInt));
            }
        }
        System.out.println(line.toString());
    }
}