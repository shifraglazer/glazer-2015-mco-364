package glazer.network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

public class ChatServer {

	private Socket socket;
	private JTextArea textArea;
	public ChatServer(JTextArea textArea) {

		try {
			//client = new Socket("192.168.1.6", 3762);
			this.textArea=textArea;
			ServerSocket serverSocket = new ServerSocket(6003); // port num sent
			socket = serverSocket.accept();
			ReadThread thread=new ReadThread(socket,this.textArea);
			thread.start();
			/*
			//client = new Socket("localhost", 3762);
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
