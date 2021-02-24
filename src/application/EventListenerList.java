/**
 *
 */
package application;

import java.util.Collections;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author Marco
 *
 */
public class EventListenerList<T extends EventListener> {
    private final Set<T> listeners = new HashSet<>();

    public void addListener(final T listener) {
        this.listeners.add(listener);
    }

    public void removeListener(final T listener) {
        this.listeners.remove(listener);
    }

    public void fireListeners(final Consumer<T> actionToPerform) {
        this.listeners.forEach(actionToPerform);
    }

    public Set<T> getListeners() {
        return Collections.unmodifiableSet(this.listeners);
    }
}
