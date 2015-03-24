package glazer.network;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatServerGui extends JFrame {

	private JTextField text;
	private JTextArea area;
	private Container container;
	private JScrollPane pane;
	private ChatServer server;

	public ChatServerGui() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("server");
		setSize(300, 300);
		setLocationRelativeTo(null);
		container = getContentPane();
		area = new JTextArea();
		area.setPreferredSize(new Dimension(150, 500));
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(area);
		text = new JTextField();
		pane = new JScrollPane();
		area.setEditable(false);
		area.add(pane);
		text.setPreferredSize(new Dimension(150, 25));
		KeyListener key = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						readText();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		};
		server = new ChatServer(area);
		text.addKeyListener(key);
		container.add(text);

	}

	public void readText() throws IOException {
		
		area.append(text.getText() + "\n");
		server.sendText(text.getText());
		text.setText("");
	}

	public static void main(String args[]) {
		ChatServerGui chat = new ChatServerGui();
		chat.setVisible(true);
	}
}
