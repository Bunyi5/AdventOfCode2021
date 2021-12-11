package advent.of.code.tasks.task11;

public class Octopus {

    private int energy;
    private boolean alreadyFlashed = false;

    public Octopus(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isAlreadyFlashed() {
        return alreadyFlashed;
    }

    public void setAlreadyFlashed(boolean alreadyFlashed) {
        this.alreadyFlashed = alreadyFlashed;
    }

    public void increaseEnergyByOne() {
        this.energy++;
    }

    @Override
    public String toString() {
        return "{energy=" + energy +
            ", alreadyFlashed=" + alreadyFlashed +
            '}';
    }
}
