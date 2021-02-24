/**
 *
 */
package application;

import java.time.Duration;

/**
 * @author Marco
 *
 */
public interface MillisecondsTimer {
    void increment();

    void reset();

    Duration getTime();
}
