package ChatController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class Repeater extends Thread {
	private int myCommunicationID;
	private final InputStream recievedStream;
	private HashMap<Integer, OutputStream> activeOutputStreams;
	private Communication l;

	public Repeater(int id, InputStream is, HashMap<Integer, OutputStream> activeOutputStreams, Communication l) {
		myCommunicationID = id;
		recievedStream = is;
		this.activeOutputStreams = activeOutputStreams;
		this.l = l;
		this.start();
	}

	public void run() {
		synchronized (l) {
			while (l.getReading() == true) {
				for (Integer i : activeOutputStreams.keySet()) {
					if (i != myCommunicationID) {
						try {
							String msg = l.getLastMessage();
							l.sendMessage(msg, activeOutputStreams.get(i), true);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				l.notify();
				try {
					l.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
