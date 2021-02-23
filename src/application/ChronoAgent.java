package application;

import java.util.Timer;
import java.util.TimerTask;

public class ChronoAgent extends EventableThread<TimerTickListener> {

    private volatile boolean stopped;
    private final long step;
    private final MillisecondsTimer millisecondsTimer;

    public ChronoAgent(final long step, final MillisecondsTimer timer) {
        this.step = step;
        this.millisecondsTimer = timer;
    }

    @Override
    public void run() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (ChronoAgent.this.stopped) {
                    timer.cancel();
                    return;
                }

                ChronoAgent.this
                        .fireListeners(listener -> listener.ticked(ChronoAgent.this.millisecondsTimer.getTime()));
                ChronoAgent.this.millisecondsTimer.increment();
            }
        }, 0, this.step);
    }

    public void forceStop() {
        this.stopped = true;
        this.interrupt();
    }
}
