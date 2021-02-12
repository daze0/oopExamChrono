package application;

public class ChronoController implements Controller {

	private static final int STEP = 1000;
	
	private ChronoAgent agent;
	private Time chronometer;
	private AppView view;
	private NotificationsAgent notificationsAgent;
	
	public ChronoController(final Time chronometer, final AppView view) {
		this.chronometer = chronometer;
		this.view = view;
		this.notificationsAgent = new NotificationsAgent(this.view, this);
		this.notificationsAgent.start();
	}

	@Override
	public void notifyStarted() {
		this.agent = new ChronoAgent(STEP, this.chronometer, this.view);
		this.agent.start();
	}

	@Override
	public void notifyReset() {
		this.chronometer.reset();
	}

	@Override
	public void notifyStopped() {
		this.agent.forceStop();
	}
	
	public String getTime() {
		return this.chronometer.toString();
	}
}
