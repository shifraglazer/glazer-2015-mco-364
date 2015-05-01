package glazer.multichat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class ReadThread extends Thread{

	private Socket socket;
	private JTextArea textArea;
	private ReaderListener listener;
	public ReadThread(Socket socket,JTextArea textArea,ReaderListener listener){
		this.textArea=textArea;
		this.socket=socket;
		this.listener=listener;
	}
	public void run(){
	
		try {
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;

			while ((line = reader.readLine()) != null) {
				listener.onLineRead(line);
				textArea.append(line+"\n");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listener.onCloseSocket(socket);
	

	}
}
