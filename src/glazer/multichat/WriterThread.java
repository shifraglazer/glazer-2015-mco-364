package glazer.multichat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
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
		synchronized(sockets){
			Iterator<Socket> iter=sockets.iterator();
			while(iter.hasNext()){
				Socket s=iter.next();
				String string;
				try {
					string = queue.take();
					try {
						
					//System.out.println("server writing: "+string);
					OutputStream stream = s.getOutputStream();
					PrintWriter write = new PrintWriter(stream);
					write.println(string);
					write.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					iter.remove();
					e.printStackTrace();
				}
				
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		}}}
}
