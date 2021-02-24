/**
 *
 */
package application.views;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import application.Controller;

/**
 * @author Marco
 *
 */
public class ChronoPanel extends ControllablePanel {

    /**
     *
     */
    private static final long serialVersionUID = 4091680812297972382L;

    private static final String WINDOW_TITLE = "OOP Exam";

    public ChronoPanel(final Controller controller) {
        super(controller, new BorderLayout());
        final JLabel centeredLabel = new JLabel(ChronoPanel.WINDOW_TITLE);
        centeredLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centeredLabel.setFont(new Font("Ubuntu Thin", Font.PLAIN, 60));
        this.add(BorderLayout.CENTER, centeredLabel);
        this.getController()
                .addChronoListener(tick -> centeredLabel
                        .setText(String.format("%2d:%2d", tick.toMinutes(), tick.toSecondsPart()).replace(' ', '0')));
    }

}
