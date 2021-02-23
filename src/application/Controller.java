package application;

public interface Controller {
    void notifyStarted();

    void notifyReset();

    void notifyStopped();

    void addChronoListener(final TimerTickListener listener);

    void removeChronoListener(final TimerTickListener listener);

    void addNotificationsListener(final NotificationListener listener);

    void removeNotificationsListener(final NotificationListener listener);
}
