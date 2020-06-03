package ChatController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class GossipConnectionHandler implements ConnectionHandler{
	private final String peer; //Name von Kommunikationspartner
	private HashMap<Integer, InputStream> activeInputStreams = new HashMap<>(); //alle InputStreams von N-1 Kommunikationspartnern
	private HashMap<Integer, OutputStream> activeOutputStreams = new HashMap<>(); //alle OutputStreams nach N-1 Kommunikationspartnern
	private int nextReadNumber = 0; //Nummer für die nächste KommunikationsID
	Communication l;

	public GossipConnectionHandler(String name) {this.peer = name;}

	@Override
	public void handleConnection(InputStream is, OutputStream os){
		int id = nextReadNumber++;
		this.activeInputStreams.put(id, is);
		this.activeOutputStreams.put(id, os);
		l = new CommunicationImpl(is, os);
		Repeater r = new Repeater(id, is, activeOutputStreams, l);
	}

	@Override
	public Communication getLogic() {
		return l;
	}
}
