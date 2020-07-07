package ChatController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class Repeater extends Thread {
	private int myCommunicationID;
	private final InputStream recievedStream; //NOT FINAL!!!!
	private HashMap<Integer, OutputStream> activeOutputStreams;
	private Communication communication;

	public Repeater(int id, InputStream is, HashMap<Integer, OutputStream> activeOutputStreams, Communication l) {
		myCommunicationID = id;
		recievedStream = is;
		this.activeOutputStreams = activeOutputStreams;
		this.communication = l;
		this.start();
	}

	public void run() {
		synchronized (communication) {
			while (communication.getReading() == true) {
				for (Integer i : activeOutputStreams.keySet()) {
					if (i != myCommunicationID) {
						try {
							String msg = communication.getLastMessage();
							communication.sendMessage(msg, activeOutputStreams.get(i), true);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				communication.notify();
				try {
					communication.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
