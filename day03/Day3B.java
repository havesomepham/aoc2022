import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Day3B {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));

        int totalPriorities = 0;
        while (reader.hasNextLine()) {
            String sackOne = reader.nextLine();
            String sackTwo = reader.nextLine();
            String sackThree = reader.nextLine();

            ArrayList<Character> firstPackages = new ArrayList<>();
            ArrayList<Character> secondPackages = new ArrayList<>();
            ArrayList<Character> thirdPackages = new ArrayList<>();

            ArrayList<Character> firstComparison = new ArrayList<>();

            for (char i : sackOne.toCharArray()) {
                if (!firstPackages.contains((Character) i)) {
                    firstPackages.add(i);
                }
            }

            for (char i : sackTwo.toCharArray()) {
                if (!secondPackages.contains((Character) i)) {
                    secondPackages.add(i);
                }
            }

            for (char i : sackThree.toCharArray()) {
                if (!thirdPackages.contains((Character) i)) {
                    thirdPackages.add(i);
                }
            }

            for (Character i : firstPackages) {
                if (secondPackages.contains(i)) {
                    firstComparison.add(i);
                }
            }

            for (Character i : thirdPackages) {
                if (firstComparison.contains(i)) {
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
