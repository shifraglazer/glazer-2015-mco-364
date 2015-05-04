package glazer.multichat;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class Client implements ReaderListener{
	private Socket socket;
	private PrintWriter writer;
	private OutputStream out;
	private JTextArea textArea;

	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		socket = new Socket("localhost", 6003);
		out = socket.getOutputStream();
		writer = new PrintWriter(out);
		this.textArea=textArea;
		ReaderThread thread = new ReaderThread(socket,this);
		thread.start();
		
	}

	public void sendMessage(String text) {
		//System.out.println("client writing: "+text);
		writer.println(text);

		writer.flush();
	}

	@Override
	public void onLineRead(String string) {
		textArea.append(string+"\n");
		//System.out.println("appending");
	
	}

	@Override
	public void onCloseSocket(Socket socket) {
		// TODO Auto-generated method stub
		
	}
}
