import java.util.HashSet;

public class CPUA {
    int clock = 0;
    int X = 1;
    HashSet<Integer> checkpoints = new HashSet<>();

    public CPUA() {
        int checkpoint = 20;
        while (checkpoint <= 220) {
            checkpoints.add(checkpoint);
            checkpoint += 40;
        }
    }

    public int noop() {
        this.clock++;
        if (this.checkpoints.contains(this.clock)) {
            return this.X * this.clock;
        }
        return 0;
    }

    public int addx(int val) {
        int signalStrength = 0;
        this.clock++;
        if (checkpoints.contains(this.clock)) {
            signalStrength = this.X * this.clock;
        }
        this.clock++;
        if (checkpoints.contains(this.clock)) {
            signalStrength = this.X * this.clock;
        }
        this.X += val;
        return signalStrength;
    }
}
