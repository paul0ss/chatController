package ChatController;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

class CommunicationTest {
	
	Communication l = new CommunicationImpl();
	
	File test;
	
	@Test
	void sendMessageTestNonRepeat() throws IOException {
		test = new File("testFile");
		String testMessage = "test";
		
		OutputStream os = new FileOutputStream (test);
		InputStream is = new FileInputStream(test);
		
		DataInputStream dis = new DataInputStream(is);
		
		String resultReturn = l.sendMessage(testMessage, os, false);
		String expectedReturn = testMessage;
		
		String resultStream = dis.readUTF() + dis.readUTF();
		String expectedStream = l.getSentMessageID() + testMessage;
		
		os.close();
		is.close();
		
		assertEquals(expectedStream, resultStream);
		assertEquals(expectedReturn, resultReturn);
	}
	
	@Test
	void readMessageTest() throws IOException {
		test = new File("testFile");
		
		String id = "74638756435";
		String testMsg = "testMessage on You";
		
		InputStream is = new FileInputStream(test);
		OutputStream os = new FileOutputStream(test);
		DataOutputStream dos = new DataOutputStream(os);
		
		dos.writeUTF(id);
		dos.writeUTF(testMsg);
		
		String resultReturn = l.readMessage(is);
		String resultID = l.getRecievedMessageID();
		
		os.close();
		is.close();
		
		assertEquals(testMsg, resultReturn);
		assertEquals(id, resultID);
	}
	
	@Test
	void sendMessageTestRepeat() throws IOException {
		test = new File("testFile");
		
		String id = "74638756435";
		String testMsg = "testMessage on You";
		
		InputStream is = new FileInputStream(test);
		OutputStream os = new FileOutputStream(test);
		DataOutputStream dos = new DataOutputStream(os);
		
		dos.writeUTF(id);
		dos.writeUTF(testMsg);
		
		l.readMessage(is);
		String resultReturn = l.sendMessage(testMsg, dos, true);
		String expectedReturn = testMsg;
		
		String result = l.getSentMessageID();
		String expected = id;
		
		os.close();
		is.close();
		
		assertEquals(expectedReturn, resultReturn);
		assertEquals(expected, result);
	}
	
	

}
