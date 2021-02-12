package application;

public interface Controller {
	void notifyStarted();
	void notifyReset();
	void notifyStopped();
	String getTime();
}
