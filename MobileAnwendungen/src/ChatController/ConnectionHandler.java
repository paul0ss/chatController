package ChatController;

import java.io.InputStream;
import java.io.OutputStream;

public interface ConnectionHandler {
	/**
	 * Bietet die Unabhängigkeit von dem Kommunikationsmedium(Bluetooth, Wi-Fi)
	 * in dem sie mit Streams arbeitet und sie verwaltet. Realissiert ausßerdem die
	 * 1:N Kommunikation, in dem die eingehende Nachricht in alle OutputStreams geschickt wird
	 * @param is InputStream(eingehender Datenfluß)
	 * @param os OutputStream(ausgehender Datenfluß)
	 */
	public void handleConnection(InputStream is, OutputStream os);
	
	/**
	 * liefert ein Communication Objekt zurück (das gerade Nachrichten empfängt)
	 * @return Communication Objekt
	 */
	public Communication getLogic();

}
