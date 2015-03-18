package glazer.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class ReadThread extends Thread{

	private Socket socket;
	private JTextArea textArea;
	public ReadThread(Socket socket,JTextArea textArea){
		this.textArea=textArea;
		this.socket=socket;
	}
	public void run(){
	
		try {
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;

			while ((line = reader.readLine()) != null) {

				textArea.append(line+"\n");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

	}
}
