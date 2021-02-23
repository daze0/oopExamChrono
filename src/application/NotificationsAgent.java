package application;

import java.time.Duration;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationsAgent extends EventableThread<NotificationListener> {

    private enum Notifications {

        FIRST_DEADLINE_15("15 minutes left to the FIRST deadline", Duration.ofSeconds(0)),
        FIRST_DEADLINE_5("5 minutes left to the FIRST deadline", Duration.ofMinutes(5)),
        FIRST_DEADLINE_END("FIRST DEADLINE reached", Duration.ofMinutes(15)),
        SECOND_DEADLINE_15("15 minutes left to the SECOND deadline", Duration.ofMinutes(55)),
        SECOND_DEADLINE_5("5 minutes left to the SECOND deadline", Duration.ofMinutes(65)),
        SECOND_DEADLINE_END("SECOND DEADLINE reached", Duration.ofMinutes(70)),
        FINAL_DEADLINE_5("5 minutes left to the end", Duration.ofMinutes(85)),
        FINAL_DEADLINE_END("END", Duration.ofMinutes(0));

        private final String description;
        private final Duration timeFromStart;

        Notifications(final String desc, final Duration timeFromStart) {
            this.description = desc;
            this.timeFromStart = timeFromStart;

        }

        public String getDescription() {
            return this.description;
        }

        public Duration getTimeFromStart() {
            return this.timeFromStart;
        }
    }

    private static final int STEP = 1000;

    private volatile boolean stopped;
    private final MillisecondsTimer millisecondsTimer;

    public NotificationsAgent(final MillisecondsTimer timer) {
        this.millisecondsTimer = timer;
    }

    @Override
    public void run() {

        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (NotificationsAgent.this.stopped) {
                    timer.cancel();
                    return;
                }

                final Duration currTime = NotificationsAgent.this.millisecondsTimer.getTime();

                Arrays.stream(Notifications.values())
                        .filter(elem -> currTime.equals(elem.getTimeFromStart()))
                        .findAny()
                        .ifPresent(notification -> NotificationsAgent.this
                                .fireListeners(listener -> listener.onNotification(notification.getDescription())));
            }
        }, 0, NotificationsAgent.STEP);
    }

    public void forceStop() {
        this.stopped = true;
        this.interrupt();
    }
}
