package glazer.multichat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class WriterThread extends Thread {

	private LinkedBlockingQueue<String> queue;
	private ArrayList<Socket> sockets;

	public WriterThread(ArrayList<Socket> sockets,
			LinkedBlockingQueue<String> queue) {
		this.sockets = sockets;
		this.queue = queue;
	}

	@Override
	public void run() {
		
		while (true) {
			try {
				String string = queue.take();
				for (Socket o : sockets) {
					//System.out.println("server writing: "+string);
					OutputStream stream = o.getOutputStream();
					PrintWriter write = new PrintWriter(stream);
					write.println(string);
					write.flush();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
