package application;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import application.views.BottomPanel;
import application.views.ChronoPanel;

/**
 * This class is responsible of the view part of the application
 */
public class AppView extends JFrame {

    /*
     * Constants
     */
    private static final long serialVersionUID = -120949648631798089L;
    private static final String WINDOW_TITLE = "OOP Exam";
    /*
     * Actual FIELDS
     */
    private final Controller chronoController;

    /*
     * Constructor: where most of this GUI object behavior is defined!
     */
    public AppView() {
        // Initial setup
        this.chronoController = new ChronoController();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationByPlatform(true);
        this.setTitle(AppView.WINDOW_TITLE);
        this.setSize(300, 200);
        /*
         * Main panel creation.
         *
         * The idea: __________________________ | | | | | mm:ss | CENTER | | | | | |
         * CENTER /___| _____ _____ |_____ \ | |start| |reset| | | | |_____| |_____| | |
         * SOUTH /___| | > SOUTH \ | notification | | |__________________________|_____|
         */
        final JPanel mainPanel = new JPanel(new BorderLayout());
        this.getContentPane().add(mainPanel);
        mainPanel.add(BorderLayout.SOUTH, new BottomPanel(this.chronoController));
        mainPanel.add(BorderLayout.CENTER, new ChronoPanel(this.chronoController));
        this.setVisible(true);
    }

}
