/**
 *
 */
package application.views;

import java.awt.BorderLayout;

import application.Controller;

/**
 * @author Marco
 *
 */
public class BottomPanel extends ControllablePanel {

    /**
     *
     */
    private static final long serialVersionUID = 5442331682176287172L;

    public BottomPanel(final Controller controller) {
        super(controller, new BorderLayout());
        this.add(BorderLayout.CENTER, new ButtonsPanel(controller));
        this.add(BorderLayout.SOUTH, new NotificationsPanel(controller));
    }

}
