package application;

public class ChronoController implements Controller {

    private static final long STEP = 1000;

    private ChronoAgent chronoAgent;
    private final MillisecondsTimer timer = new SimpleMillisecondsTimer(ChronoController.STEP);
    private NotificationsAgent notificationsAgent;
    private final EventListenerList<TimerTickListener> chronoListeners = new EventListenerList<>();
    private final EventListenerList<NotificationListener> notificationsListeners = new EventListenerList<>();

    @Override
    public void addChronoListener(final TimerTickListener listener) {
        this.chronoListeners.addListener(listener);
    }

    @Override
    public void removeChronoListener(final TimerTickListener listener) {
        this.chronoListeners.removeListener(listener);
    }

    @Override
    public void addNotificationsListener(final NotificationListener listener) {
        this.notificationsListeners.addListener(listener);
    }

    @Override
    public void removeNotificationsListener(final NotificationListener listener) {
        this.notificationsListeners.removeListener(listener);
    }

    @Override
    public void notifyStarted() {
        this.chronoAgent = new ChronoAgent(ChronoController.STEP, this.timer);
        this.notificationsAgent = new NotificationsAgent(this.timer);
        this.chronoListeners.getListeners().forEach(listener -> this.chronoAgent.addEventListener(listener));
        this.notificationsListeners.getListeners()
                .forEach(listener -> this.notificationsAgent.addEventListener(listener));
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
