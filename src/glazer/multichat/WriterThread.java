package glazer.multichat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class WriterThread extends Thread{
	
	private LinkedBlockingQueue<Object> queue;
	private ArrayList<Socket> sockets;
	
	public WriterThread(ArrayList<Socket> sockets,LinkedBlockingQueue<Object> queue){
		this.sockets=sockets;
		this.queue=queue;
	}
	
	@Override
	public void run(){
		while(true){
			try {
				Object obj=queue.take();
				for(Socket o:sockets){
					OutputStream stream=o.getOutputStream();
					ObjectOutputStream objOut=new ObjectOutputStream(stream);
					objOut.writeObject(obj);
					objOut.flush();
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
