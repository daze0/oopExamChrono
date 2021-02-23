/**
 *
 */
package application.views;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import application.Controller;

/**
 * @author Marco
 *
 */
abstract class ControllablePanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -752560006065677076L;

    private final Controller controller;

    public ControllablePanel(final Controller controller, final LayoutManager layoutManager) {
        super(layoutManager);
        this.controller = controller;
    }

    protected Controller getController() {
        return this.controller;
    }

}
