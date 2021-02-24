/**
 *
 */
package application;

import java.util.EventListener;
import java.util.function.Consumer;

/**
 * @author Marco
 *
 */
public abstract class EventableThread<T extends EventListener> extends Thread {
    private final EventListenerList<T> eventListenerList = new EventListenerList<>();

    public void addEventListener(final T listener) {
        this.eventListenerList.addListener(listener);
    }

    public void removeEventListener(final T listener) {
        this.eventListenerList.removeListener(listener);
    }

    protected void fireListeners(final Consumer<T> actionToPerform) {
        this.eventListenerList.fireListeners(actionToPerform);
    }
}
