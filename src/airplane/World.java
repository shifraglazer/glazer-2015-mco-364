package airplane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class World extends JFrame {
	private static final long serialVersionUID = 1L;

	// three panels
	private SideMap sideMap;
	private WeatherCont weather;
	private CenterMap centerMap;

	// Weather
	private WeatherBox depWeather;
	private WeatherBox desWeather;
	private WeatherBox currWeather;

	// menu
	private String view;
	private String feature;
	private JMenu viewOptions;
	private JMenuBar menu;
	private JMenu featuresOptions;

	// south cont
	private JPanel southCont;
	private JButton go;
	private JTextField location;
	private JTextField destination;

	// current address
	private String address;

	// destination address
	private String address2;

	public World() throws IOException {
		setLayout(new BorderLayout());
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// create menu bar
		featuresOptions = new JMenu("Features");
		menu = new JMenuBar();
		viewOptions = new JMenu("View");
		// TODO move jmenu so part of sidemap, not world
		setUpMenu();
		setJMenuBar(menu);

		// create the three panels and set up their location on the screen
		address = "USA";
		centerMap = new CenterMap(address);
		centerMap.setSize(new Dimension((int) getWidth() / 2, getHeight()));
		add(centerMap, BorderLayout.CENTER);

		// Point loc = centerMap.getLocationOnScreen();
		// int locx = (int) loc.getX();
		// int locy = (int) loc.getY();

		sideMap = new SideMap();
		// sideMap.setLocation(locx - 350, locy);
		add(sideMap, BorderLayout.WEST);

		weather = new WeatherCont();
		weather.setSize(new Dimension((int) getWidth() / 4, getHeight()));
		depWeather = new WeatherBox("Departure", "4.5", "57.56");
		weather.add(depWeather);
		desWeather = new WeatherBox("Destination", "4.5", "57.56");
		weather.add(desWeather);
		currWeather = new WeatherBox("Current", "7.5", "5.56");
		weather.add(currWeather);

		// weather.setLocation(locx + 550, locy);
		add(weather, BorderLayout.EAST);

		// set up south Container
		southCont = new JPanel();
		add(southCont, BorderLayout.SOUTH);
		southCont.setLayout(new FlowLayout());
		
		location = new JTextField("Departure");
		location.setColumns(20);
		location.setSelectedTextColor(Color.BLUE);
		destination = new JTextField("Destination");
		destination.setSelectedTextColor(Color.BLUE);
		destination.setColumns(20);
		go = new JButton("Go!");
		southCont.add(location);
		southCont.add(Box.createHorizontalGlue());
		southCont.add(destination);
		go.addActionListener(click);
		southCont.add(go);
		formatSouthCont();
		
		menu.add(location);
		menu.add(destination);
		menu.add(go);
		Image i= ImageIO.read(getClass().getResource("airplane.jpg"));
		this.setIconImage(i);
		setVisible(true);
	}

	public void formatSouthCont() {
		

	}

	public void setUpMenu() {
		String[] viewNames = { "Satellite", "Roadmap", "Hybrid", "Terrain" };
		String[] featuresNames = { "Roads", "Landscape", "Transit", "transit.station.airports", "pio.school" };
		JCheckBoxMenuItem[] features = new JCheckBoxMenuItem[5];
		JMenuItem[] views = new JMenuItem[4];
		featuresOptions.setToolTipText("Features to display");

		feature = "transit.station.airports";

		for (int i = 0; i < features.length; i++) {
			features[i] = new JCheckBoxMenuItem(featuresNames[i]);
			features[i].addActionListener(featuresView);
			featuresOptions.add(features[i]);
		}

		for (int i = 0; i < views.length; i++) {
			views[i] = new JMenuItem(viewNames[i]);
			views[i].setMnemonic(KeyEvent.VK_S);
			views[i].addActionListener(mapView);
			viewOptions.add(views[i]);
		}

		menu.add(viewOptions);
		menu.add(featuresOptions);
		// viewOptions.setSelectedIndex(2);
		viewOptions.setToolTipText("Map View");
		// FIXME view line wasn't doing anything
		// view = "Hybrid";
		menu.add(viewOptions);
	}

	public void setAddress(String address, String address2) throws UnsupportedEncodingException {
		if (address.equals("Departure")) {
			address = null;
		}
		if (address2.equals("Destination")) {
			address2 = null;
		}
		this.address = URLEncoder.encode(address, "UTF-8");
		this.address2 = URLEncoder.encode(address2, "UTF-8");
	}

	ActionListener click = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				setAddress(location.getText(), destination.getText());
				//Address2Thread thread = new Address2Thread(address, address2, view, sideMap);
				//thread.start();
				location.setText("Departure");
				destination.setText("Destination");
				depWeather.update(address);
				desWeather.update(address2);
				centerMap.updateMap(address);
				sideMap.updateMap(address,address2);
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		}
	};

	ActionListener featuresView = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBoxMenuItem item = (JCheckBoxMenuItem) e.getSource();
			String feature = (String) item.getText();
			try {
				updateFeature(feature);
			}
			catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
	};

	ActionListener mapView = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			String view = (String) item.getText();
			try {
				updateView(view);
			}
			catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
	};

	public void updateFeature(String feature) throws MalformedURLException {
		this.feature = feature.toLowerCase();
	}

	public void updateView(String view) throws MalformedURLException {
		this.view = view.toLowerCase();
	}
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		sideMap.paintComponent(g);
	}
	public void update() throws IOException {
		//TODO dotn want to update unless the destination changes or zoom in/out
		//sideMap.updateMap(address, address2);
		// TODO send in instead lat, log / address of plane
		//centerMap.updateMap(address);
		//currWeather.update(address2);
	}
}