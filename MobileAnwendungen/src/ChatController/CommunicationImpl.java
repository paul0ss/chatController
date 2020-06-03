package ChatController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CommunicationImpl implements Communication, Runnable {

	private String recievedMessageID; // aktuelle ID von eingehende Nachricht
	private String sentMessageID; // abgeschickte ID der Nachricht
	public String lastMessage = null; // die letzte erhaltene Nachricht

	private InputStream is;
	private OutputStream os;
	public boolean reading;
	private List<String> usedIDs = new ArrayList<>();

	public CommunicationImpl() {
	}

	public CommunicationImpl(InputStream is, OutputStream os) {
		this.is = is;
		Thread t2 = new Thread(this);
		t2.start();
	}

	@Override
	public String sendMessage(String msg, OutputStream os, boolean repeat) throws IOException {
		String id;

		// wenn die Nachricht weitergeschickt wird

		if (repeat == false) {
			long time = System.currentTimeMillis(); // Zeit in ms bis 00:00
			String date = String.format("%tm%<td", new Date()); // heutige Datum in Format mmdd

			StringBuilder idBuilder = new StringBuilder();
			idBuilder.append(time);
			//idBuilder.append(date);

			id = idBuilder.toString();

			usedIDs.add(id); // in die Liste der benutzten IDs hinzufügen
			
			System.out.println(msg); //anzeigen von abgeschickte Nachricht
		}

		// wenn es eine von mir Erzeugte Nachricht ist

		else {
			id = String.valueOf(recievedMessageID);
		}

		sentMessageID = id; // speicher die aktuelleID

		DataOutputStream dos = new DataOutputStream(os);
		dos.writeUTF(id);
		dos.writeUTF(msg);

		StringBuilder finalMessage = new StringBuilder();
		finalMessage.append(id);
		finalMessage.append(msg);

		//return finalMessage.toString();
		return msg;
	}

	@Override
	public String readMessage(InputStream is) throws IOException {
		DataInputStream dis = new DataInputStream(is);
		String id = dis.readUTF();
		if(usedIDs.contains(id)) {
			return null;
		}
		recievedMessageID = id;
		String recievedMessage = dis.readUTF();
		String finalMessage = id + recievedMessage;
		return recievedMessage;
	}

	@Override
	public String getSentMessageID() {
		return sentMessageID;
	}

	@Override
	public String getRecievedMessageID() {
		return String.valueOf(this.recievedMessageID);
	}

	@Override
	public void run() {
		synchronized (this) {
			reading = true;
			while (reading) {
				try {
					lastMessage = null;
					lastMessage = this.readMessage(is);
					if (lastMessage != null) {
						System.out.println(lastMessage);
						this.notify();
						this.wait();
					}
				} catch (InterruptedException | IOException e) {
					System.out.println("Error InputStream");
					reading = false;
				}
			}
		}
	}

	@Override
	public boolean getReading() {
		return reading;
	}

	@Override
	public String getLastMessage() {
		return this.lastMessage;
	}

}
