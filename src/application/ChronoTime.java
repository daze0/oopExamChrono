package application;

public class ChronoTime implements Time {

	private static final long SECS_PER_MINUTE = 60;
	
	private long seconds;
	private long minutes;
	
	public ChronoTime() {
	}
	
	@Override
	public void reset() {
		this.seconds = 0;
		this.minutes = 0;
	}
	
	@Override
	public void increment() {
		if(this.seconds < SECS_PER_MINUTE) {
			this.seconds += 1;
		} else {
			this.seconds = 0;
			this.minutes += 1;
		}
	}
	
	public String toString() {
		return "" + (this.minutes < 10 ? "0"+this.minutes : this.minutes) + ":" + (this.seconds < 10 ? "0"+this.seconds : this.seconds);
	}
}
