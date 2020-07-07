package ChatController;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Set;

public interface Communication {

	/**
	 * Schickt eine Nachricht ab und speichert sie lokal in einem File
	 * @param msg 
	 * @return die abgesendete Nachricht(die wird dann lokal abgespeichert)
	 * @throws IOException 
	 */
	String sendMessage(String msg, OutputStream os, boolean repeat) throws IOException;
	
	/**
	 * Liest die Nachricht aus dem InputStream
	 * @return die erhaltene Nachricht wird ausgegeben und lokal abgespeichert
	 * @throws IOException 
	 */
	String readMessage(InputStream is) throws IOException;
	
	/**
	 * Liefert die ID von zuletzt abgeschickte Nachricht
	 * @return ID als String
	 */
	String getSentMessageID();
	
	/**
	 * Liefert die ID von zuletzt erhaltene Nachricht
	 * @return ID als String
	 */
	String getRecievedMessageID();
	
	/**
	 * Sagt ob es immernoch aus dem InputStream gelesen wird
	 * @return true falls es noch liest | false falls er nicht mehr liest
	 */
	boolean getReading();
	
	/**
	 * Liefert die zuletzt erhaltene Nachricht
	 * @return
	 */
	String getLastMessage();
	
}
