package glazer.multichat;

import glazer.network.ReadThread;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextArea;

public class Client implements ReaderListener{
	private Socket socket;
	private PrintWriter writer;
	private OutputStream out;

	public Client(JTextArea textArea) {
		socket = new Socket("localhost", 6003);
		out = socket.getOutputStream();
		writer = new PrintWriter(out);
		ReadThread thread = new ReadThread(socket, textArea,this);
		thread.start();
	}

	public void sendMessage(String text) {

		writer.println(text);

		writer.flush();
	}

	@Override
	public void onLineRead(String string) {
	sendMessage(string);
	}

	@Override
	public void onCloseSocket(Socket socket) {
		// TODO Auto-generated method stub
		
	}
}
