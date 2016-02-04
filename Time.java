
public class Time implements Runnable {
	private Thread t;
	private int seconds = 0;
	private int minutes = 0;
	private boolean suspendFlag;
	private boolean stopped;

	Time(String threadname) {
		t = new Thread(this);
		suspendFlag = false;
		stopped = false;
		t.start();
	}

	public void run() {
		try {
			while (!stopped) {
				Thread.sleep(1000);
				seconds++;
				if (seconds == 59) {
					seconds = -1;
					minutes++;
				}
				synchronized (this) {
					while (suspendFlag) {
						wait();
					}
				}
			}
		} catch (InterruptedException e) {
		}
	}

	public int getSeconds() {
		return seconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public void mysuspend() {
		suspendFlag = true;
	}

	public synchronized void myresume() {
		suspendFlag = false;
		notify();
	}

	public void stop() {
		stopped = true;
	}
}