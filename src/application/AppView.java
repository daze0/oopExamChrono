package application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This class is responsible of the view part
 * of the application
 */
public class AppView extends JFrame {
	/**
	 * This type right here represents the
	 * two possible labels on which the method
	 * {@link AppView#updateLabel(Labels, String)} operates.
	 */
	public enum Labels {
		TITLE,
		NOTIFICATIONS;
	}
	/*
	 * Constants
	 */
	private static final long serialVersionUID = -120949648631798089L;
	private static final String WINDOW_TITLE = "OOP Exam";
	/*
	 * Actual FIELDS
	 */
	private Controller chronometer;
	private final JLabel centeredLabel = new JLabel(WINDOW_TITLE);
	private final JLabel notificationLabel = new JLabel();
	/*
	 * Constructor: where most of this GUI object behavior is defined!
	 * TODO: try to wrap some parts of 
	 * 		 this code in single private methods.
	 */
	public AppView() {
		// Initial setup
		this.chronometer = new ChronoController(new ChronoTime(), this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationByPlatform(true);
		this.setTitle(WINDOW_TITLE);
		this.setSize(300, 200);
		/*
		 * Main panel creation.
		 * 
		 * The idea:
		 * 			 	 __________________________
		 * 	   		    |                          |
		 * 	   			|                          |
		 * 	   			|          mm:ss           |  CENTER
		 * 				|                          |
		 * 	 	  	 	|                          |
		 * 	  	  		|                          |
		 * 	CENTER	/___|     _____       _____    |_____
		 * 	  		\	|    |start|     |reset|   |     |
		 * 	  			|    |_____|	 |_____|   |     |
		 *	SOUTH   /___|						   |      >  SOUTH
		 * 	   		\	|		notification	   |     |
		 * 	   			|__________________________|_____|
		 */
		final JPanel mainPanel = new JPanel(new BorderLayout());
		this.getContentPane().add(mainPanel);
		/*
		 * GUI Layout 
		 */
		final JPanel southPanel = new JPanel(new BorderLayout());
		final JPanel btnsPanel = new JPanel(new FlowLayout());
		final JButton startBtn = new JButton("start");
		startBtn.addActionListener(al -> {
			if(startBtn.getText().equals("start")) {
				this.chronometer.notifyStarted();
				startBtn.setText("stop");
			} else {
				this.chronometer.notifyStopped();
				startBtn.setText("start");
			}
		});
		final JButton resetBtn = new JButton("reset");
		resetBtn.addActionListener(al -> {
			this.chronometer.notifyReset();
			this.updateLabel(Labels.TITLE, "00:00");
		});
		btnsPanel.add(startBtn);
		btnsPanel.add(resetBtn);
		southPanel.add(BorderLayout.CENTER, btnsPanel);
		final JPanel msgPanel = new JPanel(new FlowLayout());
		this.notificationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.notificationLabel.setFont(new Font("Ubuntu Thin", Font.ITALIC, 24));
		msgPanel.add(this.notificationLabel);
		southPanel.add(BorderLayout.SOUTH, msgPanel);
		mainPanel.add(BorderLayout.SOUTH, southPanel);
		final JPanel centerPanel = new JPanel(new BorderLayout());
		this.centeredLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.centeredLabel.setFont(new Font("Ubuntu Thin", Font.PLAIN, 60));
		centerPanel.add(BorderLayout.CENTER, this.centeredLabel);
		mainPanel.add(BorderLayout.CENTER, centerPanel);
		this.setVisible(true);
	}
	
	/**
	 * 
	 * @param label
	 * 	the label to update
	 * @param newLabelText
	 * 	the text to be shown in the label
	 * Updates a label with the given text.
	 */
	public void updateLabel(final Labels label, final String newLabelText) {
		if (label == Labels.TITLE) {
			this.centeredLabel.setText(newLabelText);
		} else if (label == Labels.NOTIFICATIONS) {
			this.notificationLabel.setText(newLabelText);
		} 
	}
	
	
	
}
