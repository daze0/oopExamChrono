package application;

import javax.swing.SwingUtilities;

import application.AppView.Labels;

public class ChronoAgent extends Thread {
	
	private volatile boolean stopped;
	private int step;
	private Time chronometer;
	private AppView view;
	
	public ChronoAgent(final int step, final Time chrono, final AppView view) {
		this.step = step;
		this.chronometer = chrono;
		this.view = view;
	}
	
	public void run() {
		while(!this.stopped) {
			chronometer.increment();
			SwingUtilities.invokeLater(() -> {
				this.view.updateLabel(Labels.TITLE, this.chronometer.toString());
			});
			try {
				Thread.sleep(this.step);
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
