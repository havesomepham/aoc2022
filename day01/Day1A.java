import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Day1A {

    public static void main(String[] args) throws IOException {
        Scanner fileIn = new Scanner(new File("day1/input.txt"));

        int mostCalories = 0;
        int runningCalories = 0;
        while (fileIn.hasNextLine()) {
            String nextLineContent = fileIn.nextLine();
            if (!nextLineContent.isEmpty()) { // if there is more data with this elf
                runningCalories += Integer.parseInt(nextLineContent);
            } else { // if we are done with this elf
                if (runningCalories > mostCalories) { // if this elf has more calories than any
                                                      // other
                    mostCalories = runningCalories;
                }
                runningCalories = 0;
            }
        }

        System.out.println("Max calories = " + mostCalories);
        fileIn.close();
    }
}
