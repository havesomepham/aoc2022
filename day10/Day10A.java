import java.util.*;
import java.io.*;

public class Day10A {

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));
        CPUA curr = new CPUA();
        int sumOfSignalStrengths = 0;
        while (reader.hasNextLine()) {
            String command = reader.next();
            if (command.equals("noop")) {
                sumOfSignalStrengths += curr.noop();
            } else {
                int nextInt = reader.nextInt();
                sumOfSignalStrengths += curr.addx(nextInt);
            }
        }
        System.out.println(sumOfSignalStrengths);
    }
}