package glazer.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

public class PaintFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;
	private JTextField color;
	private JMenuBar menu;
	private JButton rectangle;
	private JButton pencil;
	public PaintFrame() {
		width = 800;
		height = 600;
		setSize(width, height);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		color = new JTextField("Enter color");
		menu=new JMenuBar();
		rectangle=new JButton("Draw Rect");
		pencil=new JButton("Draw Pencil");
		menu.add(rectangle);
		menu.add(pencil);
		menu.add(color);
		setJMenuBar(menu);
		setLayout(new BorderLayout());
		Canvas canvas = new Canvas(600, 600);
		add(canvas, BorderLayout.CENTER);
		DrawListener listener = new DrawListener(canvas);
		canvas.addMouseListener(listener);
		canvas.addMouseMotionListener(listener);
		ActionListener rect=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			listener.setFunction("rectangle");
			}
			
		};
		ActionListener pencilListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			listener.setFunction("pencil");
			}
			
		};
		pencil.addActionListener(pencilListener);
		rectangle.addActionListener(rect);
		KeyListener listenColor=new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
			
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					JTextField field=(JTextField) e.getSource();
				
					Color color=Color.BLACK;
					switch(field.getText()){
					case "Red":
						color=Color.RED;
						break;
					case "Orange":
						color=Color.ORANGE;
						break;
					case "Yellow":
						color=Color.YELLOW;
						break;
					case "Green":
						color=Color.GREEN;
						break;
					case "Blue":
						color=Color.BLUE;
						break;
					case "Pink":
						color=Color.PINK;
						break;
					}
					listener.setColor(color);
					field.setText("");
				}
			}
			
		};
		color.addKeyListener(listenColor);
	}
	


	public static void main(String[] args) {

		PaintFrame frame = new PaintFrame();
		frame.setVisible(true);
	}

}