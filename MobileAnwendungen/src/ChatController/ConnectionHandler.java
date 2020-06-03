package ChatController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ConnectionHandler {

	public void handleConnection(InputStream is, OutputStream os);
	
	public Communication getLogic();
}
