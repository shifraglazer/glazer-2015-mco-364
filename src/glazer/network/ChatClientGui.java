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


public class ChatClientGui extends JFrame{

		private JTextField text;
		private JTextArea area;
		private Container container;
		private JScrollPane pane;
		private ChatClient client;

		public ChatClientGui() {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle("client");
			setLocationRelativeTo(null);
			setSize(300, 300);
			container = getContentPane();
			area = new JTextArea();
			area.setPreferredSize(new Dimension(150, 500));
			container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
			container.add(area);
			text = new JTextField();
			pane = new JScrollPane();
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
			client = new ChatClient(area);
			text.addKeyListener(key);
			container.add(text);

		}

		public void readText() throws IOException {
			
			area.append(text.getText() + "\n");
			client.sendText(text.getText());
			text.setText("");
		}

		public static void main(String args[]) {
			ChatClientGui chat = new ChatClientGui();
			chat.setVisible(true);
		}
	}

