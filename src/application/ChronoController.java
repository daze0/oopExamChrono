package application;

public class ChronoController implements Controller {

    private static final long STEP = 1000;

    private final ChronoAgent chronoAgent;
    private final MillisecondsTimer timer = new SimpleMillisecondsTimer(ChronoController.STEP);
    private final NotificationsAgent notificationsAgent;

    public ChronoController() {
        this.notificationsAgent = new NotificationsAgent(this.timer);
        this.chronoAgent = new ChronoAgent(ChronoController.STEP, this.timer);
    }

    @Override
    public void addChronoListener(final TimerTickListener listener) {
        this.chronoAgent.addEventListener(listener);
    }

    @Override
    public void removeChronoListener(final TimerTickListener listener) {
        this.chronoAgent.removeEventListener(listener);
    }

    @Override
    public void addNotificationsListener(final NotificationListener listener) {
        this.notificationsAgent.addEventListener(listener);
    }

    @Override
    public void removeNotificationsListener(final NotificationListener listener) {
        this.notificationsAgent.removeEventListener(listener);
    }

    @Override
    public void notifyStarted() {
        this.chronoAgent.start();
        this.notificationsAgent.start();
    }

    @Override
    public void notifyReset() {
        this.timer.reset();
    }

    @Override
    public void notifyStopped() {
        this.chronoAgent.forceStop();
        this.notificationsAgent.forceStop();
    }
}
