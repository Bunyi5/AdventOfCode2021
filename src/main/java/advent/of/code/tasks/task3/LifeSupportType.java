package advent.of.code.tasks.task3;

public enum LifeSupportType {
    OXYGEN_GENERATOR {
        @Override
        public boolean apply(int x1, int x2) {
            return x1 >= x2;
        }
    },
    CO2_SCRUBBER {
        @Override
        public boolean apply(int x1, int x2) {
            return x1 < x2;
        }
    };

    public abstract boolean apply(int x1, int x2);
}
