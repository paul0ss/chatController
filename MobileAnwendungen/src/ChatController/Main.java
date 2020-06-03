package ChatController;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		//ConnectionHandler abc = new GossipConnectionHandler("abc");
		//abc.handleConnection(System.in, System.out);
		String date = LocalDate.now().toString();
		String finalDate = String.format("%tm%<td", new Date());
		System.out.println(finalDate);//heutige Datum in Format yyyymmdd
	}

}
