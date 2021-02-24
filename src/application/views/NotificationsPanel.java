/**
 *
 */
package application.views;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import application.Controller;

/**
 * @author Marco
 *
 */
public class NotificationsPanel extends ControllablePanel {

    /**
     *
     */
    private static final long serialVersionUID = -4618904409687767443L;

    public NotificationsPanel(final Controller controller) {
        super(controller, new FlowLayout());
        final JLabel notificationsLabel = new JLabel();
        notificationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notificationsLabel.setFont(new Font("Ubuntu Thin", Font.ITALIC, 24));
        this.add(notificationsLabel);
        this.getController().addNotificationsListener(notification -> notificationsLabel.setText(notification));
    }

}
