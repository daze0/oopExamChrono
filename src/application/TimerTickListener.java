/**
 *
 */
package application;

import java.time.Duration;
import java.util.EventListener;

/**
 * @author Marco
 *
 */
@FunctionalInterface
public interface TimerTickListener extends EventListener {
    void ticked(Duration duration);
}
