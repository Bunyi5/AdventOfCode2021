package advent.of.code.helper;

import java.util.concurrent.TimeUnit;

public class Timer {

    private static long start = 0L;
    private static long end = 0L;

    public static void startTimer() {
        start = System.nanoTime();
    }

    public static void endTimer(TimeUnit timeUnit) {
        end = System.nanoTime();
        System.out.println("Timer: " + timeUnit.convert(end - start, TimeUnit.NANOSECONDS));
    }
}
