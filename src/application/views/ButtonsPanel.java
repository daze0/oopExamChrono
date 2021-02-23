/**
 *
 */
package application.views;

import java.awt.FlowLayout;

import javax.swing.JButton;

import application.Controller;

/**
 * @author Marco
 *
 */
public class ButtonsPanel extends ControllablePanel {

    /**
     *
     */
    private static final long serialVersionUID = -3644692585880678106L;

    public ButtonsPanel(final Controller controller) {
        super(controller, new FlowLayout());
        final JButton startBtn = new JButton("start");
        startBtn.addActionListener(al -> {
            if (startBtn.getText().equals("start")) {
                this.getController().notifyStarted();
                startBtn.setText("stop");
            } else {
                this.getController().notifyStopped();
                startBtn.setText("start");
            }
        });
        final JButton resetBtn = new JButton("reset");
        resetBtn.addActionListener(al -> {
            this.getController().notifyReset();
        });
        this.add(startBtn);
        this.add(resetBtn);
    }

}
