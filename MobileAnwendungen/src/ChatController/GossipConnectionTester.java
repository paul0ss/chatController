package ChatController;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class GossipConnectionTester {

	@Test
	void handleConnectionTest() throws IOException, InterruptedException {
		ConnectionHandler a = new GossipConnectionHandler("a");
		ConnectionHandler b = new GossipConnectionHandler("b");
		ConnectionHandler c = new GossipConnectionHandler("c");
		
		int port = 7777;
		
		//a <-> b
		TCPChannel a2b = new TCPChannel(port, true, "a2b");
		TCPChannel b2a = new TCPChannel(port, false, "b2a");
		
		//a <-> c
		port++;
		TCPChannel a2c = new TCPChannel(port, true, "a2c");
		TCPChannel c2a = new TCPChannel(port, false, "c2a");
		
		//b <-> c
		port++;
		TCPChannel b2c = new TCPChannel(port, true, "b2c");
		TCPChannel c2b = new TCPChannel(port, false, "c2b");
		
		a2b.start();
		b2a.start();
		
		a2c.start();
		c2a.start();
		
		b2c.start();
		c2b.start();
		
		
		//wait for connection
		a2b.waitForConnection();
		b2a.waitForConnection();
		
		a2c.waitForConnection();
		c2a.waitForConnection();
		
		b2c.waitForConnection();
		c2b.waitForConnection();
		
		//connect to application
		a.handleConnection(a2b.getInputStream(), a2b.getOutputStream());
		b.handleConnection(b2a.getInputStream(), b2a.getOutputStream());

		a.handleConnection(a2c.getInputStream(), a2c.getOutputStream());
		c.handleConnection(c2a.getInputStream(), c2a.getOutputStream());

		b.handleConnection(b2c.getInputStream(), b2c.getOutputStream());
		c.handleConnection(c2b.getInputStream(), c2b.getOutputStream());
		
		//finally - start gossip
		Communication l = a.getLogic();
		l.sendMessage("Hello there", a2b.getOutputStream(), false);
		
		Thread.sleep(100);
	}

}
