import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Day3A {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));

        int totalPriorities = 0;
        while (reader.hasNextLine()) {
            String sack = reader.nextLine();
            int sackLength = sack.length();
            String firstCompartment = sack.substring(0, sackLength / 2);
            String secondCompartment = sack.substring(sackLength / 2);

            ArrayList<Character> firstPackages = new ArrayList<>();
            ArrayList<Character> secondPackages = new ArrayList<>();

            for (char i : firstCompartment.toCharArray()) {
                if (!firstPackages.contains((Character) i)) {
                    firstPackages.add(i);
                }
            }

            for (char i : secondCompartment.toCharArray()) {
                if (!secondPackages.contains((Character) i)) {
                    secondPackages.add(i);
                }
            }

            for (Character i : firstPackages) {
                if (secondPackages.contains(i)) {
                    totalPriorities += getCharacterIndex(i);
                }
            }
        }
        System.out.println(totalPriorities);
    }

    private static int getCharacterIndex(Character i) {
        if (Character.isUpperCase(i)) {
            return i.charValue() - 38;
        } else {
            return i.charValue() - 96;
        }
    }
}
