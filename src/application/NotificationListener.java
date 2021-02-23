/**
 *
 */
package application;

import java.util.EventListener;

/**
 * @author Marco
 *
 */
public interface NotificationListener extends EventListener {
    void onNotification(String notification);
}
