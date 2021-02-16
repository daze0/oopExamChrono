package application;

import javax.swing.SwingUtilities;

import application.AppView.Labels;

public class NotificationsAgent extends Thread {
	
	private enum Notifications {
		
		FIRST_DEADLINE_15("15 minutes left to the FIRST deadline"),
		FIRST_DEADLINE_5("5 minutes left to the FIRST deadline"),
		FIRST_DEADLINE_END("FIRST DEADLINE reached"),
		SECOND_DEADLINE_15("15 minutes left to the SECOND deadline"),
		SECOND_DEADLINE_5("5 minutes left to the SECOND deadline"),
		SECOND_DEADLINE_END("SECOND DEADLINE reached"), 
		FINAL_DEADLINE_5("5 minutes left to the end"),
		FINAL_DEADLINE_END("END");
		
		private final String description;
		
		Notifications(final String desc) {
			this.description = desc;
		}
		
		public String getDescription() {
			return this.description;
		}
	}
	
	private static final int STEP = 1000;
	
	private volatile boolean stopped;
	private AppView view;
	private Controller chronometer;
	
	public NotificationsAgent(final AppView view, final Controller chronometer) {
		this.view = view;
		this.chronometer = chronometer;
	}
	
	@Override
	public void run() {
		while (!this.stopped) {
			switch (this.chronometer.getTime()) {
			case "00:10":
				SwingUtilities.invokeLater(() -> {
					this.view.updateLabel(Labels.NOTIFICATIONS, Notifications.FIRST_DEADLINE_15.getDescription());
					/*
					 * XXX: Find a way to resize the window 
					 * in order to show the entire notification.
					 */
				});
				break;
			case "40:00":
				SwingUtilities.invokeLater(() -> {
					this.view.updateLabel(Labels.NOTIFICATIONS, Notifications.FIRST_DEADLINE_5.getDescription());
				});
				break;
			case "45:00":
				SwingUtilities.invokeLater(() -> {
					this.view.updateLabel(Labels.NOTIFICATIONS, Notifications.FIRST_DEADLINE_END.getDescription());
				});
				break;
			case "55:00":
				SwingUtilities.invokeLater(() -> {
					this.view.updateLabel(Labels.NOTIFICATIONS, Notifications.SECOND_DEADLINE_15.getDescription());
				});
				break;
			case "65:00":
				SwingUtilities.invokeLater(() -> {
					this.view.updateLabel(Labels.NOTIFICATIONS, Notifications.SECOND_DEADLINE_5.getDescription());
				});
				break;
			case "70:00":
				SwingUtilities.invokeLater(() -> {
					this.view.updateLabel(Labels.NOTIFICATIONS, Notifications.SECOND_DEADLINE_END.getDescription());
				});
				break;
			case "85:00":
				SwingUtilities.invokeLater(() -> {
					this.view.updateLabel(Labels.NOTIFICATIONS, Notifications.FINAL_DEADLINE_5.getDescription());
				});
				break;
			case "90:00":
				SwingUtilities.invokeLater(() -> {
					this.view.updateLabel(Labels.NOTIFICATIONS, Notifications.FINAL_DEADLINE_END.getDescription());
				});
				break;
			}
			try {
				Thread.sleep(STEP);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void forceStop() {
		this.stopped = true;
		this.interrupt();
	}
}
