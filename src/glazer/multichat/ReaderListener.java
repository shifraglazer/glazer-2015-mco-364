package glazer.multichat;

import java.net.Socket;

public interface ReaderListener {

	void onObjectRead(Object obj);

	void onCloseSocket(Socket socket);

}
