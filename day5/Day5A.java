import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day5A {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));

        ArrayList<Deque<Character>> stacks = new ArrayList<>(9);

        ArrayList<String> initialContents = new ArrayList<>(9);
        initialContents.add("BWN");
        initialContents.add("LZSPTDMB");
        initialContents.add("QHZWR");
        initialContents.add("WDVJZR");
        initialContents.add("SHMB");
        initialContents.add("LGNJHVPB");
        initialContents.add("JQZFHDLS");
        initialContents.add("WSFJGQB");
        initialContents.add("ZWMSCDJ");

        // crate loading
        for (int i = 0; i < 9; i++) {
            stacks.add(new ArrayDeque<Character>());
            for (Character crate : initialContents.get(i).toCharArray()) {
                stacks.get(i).add(crate);
            }
        }

        while (reader.hasNextLine()) {
            reader.next();
            int numberOfCrates = reader.nextInt();
            reader.next();
            int initStackIndex = reader.nextInt() - 1; // takes care of the off-by-one error
            reader.next();
            int finalStackIndex = reader.nextInt() - 1;

            for (int i = 0; i < numberOfCrates; i++) {
                Character crate = stacks.get(initStackIndex).removeLast();
                stacks.get(finalStackIndex).addLast(crate);
            }
        }

        StringBuilder output = new StringBuilder();
        for (Deque<Character> stack : stacks) {
            output.append(stack.removeLast().toString());
        }
        System.out.println(output.toString());
    }
}
