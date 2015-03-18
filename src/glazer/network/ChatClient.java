package glazer.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

public class ChatClient {

	private Socket socket;

	public ChatClient(JTextArea textArea) {

		try {
			//client = new Socket("192.168.1.6", 3762);
			
		
			socket = new Socket("localhost", 3765);
			ReadThread thread=new ReadThread(socket,textArea);
			thread.start();
			/*
			// in

				InputStream in = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				String line;

				while ((line = reader.readLine()) != null) {

					System.out.println(line);
					textArea.append(line+"\n");

				}

*/
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public void recieveMessages(){
		
	
	}
	public void sendText(String text) throws IOException {

		OutputStream out = socket.getOutputStream();

		PrintWriter writer = new PrintWriter(out);

		writer.println(text);

		writer.flush();

	}
}
