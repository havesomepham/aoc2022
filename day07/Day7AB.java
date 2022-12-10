import java.io.*;
import java.util.*;

public class Day7AB {
    public static void main(String[] args) throws IOException, Exception {
        Scanner reader = new Scanner(new File("input.txt"));
        reader.nextLine();

        Tree tree = new Tree("/");

        while (reader.hasNextLine()) {
            try {
                String command = reader.next();
                if (command.equals("cd")) {
                    String name = reader.next();
                    tree.changeDirectory(name);
                } else if (command.equals("ls")) {
                    int size;
                    String next = reader.next();
                    while (!next.equals("$")) {
                        try {
                            size = Integer.parseInt(next);
                            String name = reader.next();
                            tree.setCurrentNodeChild(name, size);
                        } catch (NumberFormatException e) { // if size is not an integer, it is a name
                            String name = reader.next();
                            tree.setCurrentNodeChild(name);
                        }
                        next = reader.next();
                    }
                }
            } catch (NoSuchElementException e) {
            }
        }

        System.out.println(tree.getValidTotal(tree.root));

        System.out.println(tree.getMaxSize(tree.root));
    }

}
