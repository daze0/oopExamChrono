/**
 *
 */
package application;

import java.time.Duration;

/**
 * @author Marco
 *
 */
public class SimpleMillisecondsTimer implements MillisecondsTimer {

    private long milliseconds = 0;
    private final long step;

    public SimpleMillisecondsTimer(final long step) {
        this.step = step;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void increment() {
        this.milliseconds += this.step;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Duration getTime() {
        return Duration.ofMillis(this.milliseconds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void reset() {
        this.milliseconds = 0;
    }

}
