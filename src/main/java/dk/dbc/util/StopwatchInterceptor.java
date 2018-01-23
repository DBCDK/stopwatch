/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt
 */

package dk.dbc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.concurrent.TimeUnit;

@Timed
@Interceptor
public class StopwatchInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(StopwatchInterceptor.class);

    @AroundInvoke
    public Object time(InvocationContext invocationContext) throws Exception {
        final String systemClassName = invocationContext.getMethod().getDeclaringClass().getCanonicalName();
        final String systemMethodName = invocationContext.getMethod().getName();
        final TimeUnit timeUnit = getTimeUnit(invocationContext);

        final Stopwatch stopwatch = new Stopwatch();
        final Object businessCall;
        try {
            businessCall = invocationContext.proceed();
        } finally {
            LOGGER.info("method call {}.{} took {} {}",
                    systemClassName, systemMethodName, stopwatch.getElapsedTime(timeUnit), timeUnit);
        }
        return businessCall;
    }

    private TimeUnit getTimeUnit(InvocationContext invocationContext) {
        final Timed timedAnnotation = invocationContext.getMethod().getAnnotation(Timed.class);
        if (timedAnnotation != null) {
            return timedAnnotation.timeUnit();
        }
        return TimeUnit.MILLISECONDS;
    }
}
