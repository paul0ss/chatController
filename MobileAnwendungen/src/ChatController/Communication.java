package ChatController;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Set;

public interface Communication {
//	
//	/**
//	 * stellt die vorherige Sitzung wieder her(holt alle Kontakte und alle Nachrichten aus dem Speicher)
//	 */
//	public default void restoreSession() {
//		
//	}
	
	/**
	 * Schickt eine Nachricht ab und speichert sie lokal in einem File
	 * @param msg 
	 * @return die abgesendete Nachricht(die wird dann lokal abgespeichert)
	 * @throws IOException 
	 */
	public String sendMessage(String msg, OutputStream os, boolean repeat) throws IOException;
	
	/**
	 * Liest die Nachricht aus dem InputStream
	 * @return die erhaltene Nachricht wird ausgegeben und lokal abgespeichert
	 * @throws IOException 
	 */
	public String readMessage(InputStream is) throws IOException;

	String getSentMessageID();
	
	String getRecievedMessageID();
	
	public boolean getReading();
	
	public String getLastMessage();
	
//	/**
//	 * Schickt den File ab
//	 * @param file
//	 * return Filename um sie im Log zu speichern 
//	 */
//	public String sendFile(File file, OutputStream os);
//	
//	/**
//	 * zeigt die verfügbare Geräte(Implementierung durch Network package)
//	 */
//	public void showPeers(HashMap<String, String> peers);
//	
//	/**
//	 * macht den Chat mit einem unbekannten Gerät auf(Implementierung durch Network package)
//	 * @param mac MAC-Adresse des Geräts
//	 */
//	public void chatWith(byte[] mac);
//	
//	/**
//	 * macht den Chat mit einem bekannte Gerät auf(Implementierung durch Network package)
//	 * @param name die Name des Geräts im Speicher
//	 */
//	public void chatWith(String name);
//	
//	/**
//	 * macht den Chat mit mehreren Teilnehmer auf(Implementierung durch Network package)
//	 * @param peers
//	 */
//	public void chatGroup(Set peers);
}
