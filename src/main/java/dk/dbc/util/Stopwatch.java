/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt
 */

package dk.dbc.util;

import java.util.concurrent.TimeUnit;

/**
 * Measures elapsed (wall-clock) time
 */
public class Stopwatch {
    private long startTime;

    public Stopwatch() {
        startTime = System.nanoTime();
    }

    public Stopwatch reset() {
        startTime = System.nanoTime();
        return this;
    }

    /**
     * Gets the elapsed time (in nanoseconds) since this {@link Stopwatch}
     * was initialized or last reset
     * @return elapsed time in nanoseconds
     */
    public long getElapsedTimeNano() {
        return System.nanoTime() - startTime;
    }

    /**
     * Gets the elapsed time since this {@link Stopwatch} was initialized
     * or last reset
     * @param unit unit of elapsed time granularity
     * @return elapsed time in specified granularity
     */
    public long getElapsedTime(TimeUnit unit) {
        return unit.convert(getElapsedTimeNano(), TimeUnit.NANOSECONDS);
    }
}
