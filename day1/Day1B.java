import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Day1B {

    public static void main(String[] args) throws IOException {
        Scanner keyIn = new Scanner(System.in);
        File inFile = new File("day1/input.txt");
        Scanner fileIn = new Scanner(inFile);

        int[] mostCalories = new int[3]; // {A, B, C} where A < B < C
        int runningCalories = 0;
        while (fileIn.hasNextLine()) {
            String nextLineContent = fileIn.nextLine();
            if (!nextLineContent.isEmpty()) { // if there is more data with this elf
                runningCalories += Integer.parseInt(nextLineContent);
            } else { // if we are done with this elf
                if (runningCalories > mostCalories[0]) { // if this elf has more calories than any
                                                         // other
                    if (runningCalories > mostCalories[1]) {
                        if (runningCalories > mostCalories[2]) {
                            mostCalories[0] = mostCalories[1];
                            mostCalories[1] = mostCalories[2];
                            mostCalories[2] = runningCalories;
                        } else {
                            mostCalories[0] = mostCalories[1];
                            mostCalories[1] = runningCalories;
                        }
                    } else {
                        mostCalories[0] = runningCalories;
                    }
                }
                runningCalories = 0;
            }
        }

        int totalCalories = 0;
        for (int i : mostCalories) {
            totalCalories += i;
        }
        System.out.println("Total calories = " + totalCalories);
        keyIn.close();
        fileIn.close();
    }
}
