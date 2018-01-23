/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt
 */

package dk.dbc.util;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;


/**
 * Measures elapsed CPU time
 */
public class StopwatchCPU {
    private final ThreadMXBean threadTimer;
    private long startTime;

    public StopwatchCPU() {
        this.threadTimer = ManagementFactory.getThreadMXBean();
        this.startTime = threadTimer.getCurrentThreadCpuTime();
    }

    public StopwatchCPU reset() {
        startTime = threadTimer.getCurrentThreadCpuTime();
        return this;
    }

    /**
     * Gets the elapsed CPU time (in nanoseconds) since this {@link StopwatchCPU}
     * was initialized or last reset
     * @return elapsed time in nanoseconds
     */
    public long getElapsedTimeNano() {
        return threadTimer.getCurrentThreadCpuTime() - startTime;
    }

    /**
     * Gets the elapsed CPU time since this {@link StopwatchCPU} was initialized
     * or last reset
     * @param unit unit of elapsed time granularity
     * @return elapsed time in specified granularity
     */
    public long getElapsedTime(TimeUnit unit) {
        return unit.convert(getElapsedTimeNano(), TimeUnit.NANOSECONDS);
    }
}
