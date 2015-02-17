package airplane;

import java.awt.Font;
import java.awt.GridLayout;
import java.net.MalformedURLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class WeatherBox extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel titleLabel;
	private WeatherInfo info;

	public WeatherBox(String title, String lat, String log) throws MalformedURLException {
		setLayout(new GridLayout(2,1));
		titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(titleLabel);
		info = new WeatherInfo(lat, log);
		add(info);
	}
	
	public void update(String lat, String log) throws MalformedURLException{
		remove(info);
		info = new WeatherInfo(lat, log);
		add(info);
	}
	
	public void update(String address) throws MalformedURLException {
		remove(info);
		info = new WeatherInfo(address);
		add(info);
	}
}
