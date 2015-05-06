package glazer.multichat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class ChatServer implements ReaderListener{

	private Socket socket;
	private LinkedBlockingQueue<String> queue;
	private ArrayList<Socket> sockets;
	private WriterThread write;
	public ChatServer() {
		queue= new LinkedBlockingQueue<String>();
		sockets= new ArrayList<Socket>();
		write=new WriterThread(sockets,queue);
		write.start();
		try {
			//client = new Socket("192.168.1.6", 3762);
			
			ServerSocket serverSocket = new ServerSocket(6003); // port num sent
			while( true){
			socket = serverSocket.accept();
			synchronized(sockets){
			sockets.add(socket);
			}
			ReaderThread thread=new ReaderThread(socket,this);
			thread.start();
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	

	@Override
	public void onLineRead(String line) {
		queue.add(line);
	}


	@Override
	public void onCloseSocket(Socket socket) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String args[]){
		ChatServer chat=new ChatServer();
	}



}
