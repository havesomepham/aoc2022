import java.util.HashSet;

public class CPUB {
    int clock = 0;
    int X = 1;
    HashSet<Integer> checkpoints = new HashSet<>();
    boolean firstAddxCycle = true;

    public CPUB() {
        int checkpoint = 0;
        while (checkpoint <= 220) {
            checkpoints.add(checkpoint);
            checkpoint += 40;
        }
    }

    public String noop() {
        this.clock++;
        return checkPixel(clock);
    }

    public String addx(int val) {
        this.clock++;
        if (firstAddxCycle) {
            firstAddxCycle = false;
            return checkPixel(clock);
        } else {
            firstAddxCycle = true;
            String output = checkPixel(clock);
            this.X += val;
            return output;
        }
        
    }

    public boolean checkCheckpoints(int pixel) {
        return checkpoints.contains(pixel) ? true : false;
    }

    public String checkPixel(int position) {
        int linePosition = position % 40;
        if (Math.abs(linePosition - 1 - this.X) <= 1) {
            if (checkCheckpoints(linePosition - 1)) {
                return "\n#";
            }
            return "#";
        } else {
            if (checkCheckpoints(linePosition - 1)) {
                return "\n.";
            }
            return ".";
        }
    }
}
