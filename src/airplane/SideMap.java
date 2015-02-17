package airplane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class SideMap extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel mapImg;
	private int zoom;
	private String view;
	private String path;
	private Address2Thread load;
	private String address;
	private String address2;
	private Airplane plane;

	// menu
	private String feature;
	private JMenu viewOptions;
	private JMenuBar menu;
	private JMenu featuresOptions;

	// north cont
	private JPanel northCont;
	private JButton zoomout;
	private JButton zoomin;

	public SideMap() throws IOException {
		setSize(300, 600);
		setLayout(new BorderLayout());
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		mapImg = new JLabel();
		add(mapImg, BorderLayout.CENTER);
		plane=new Airplane(20);
		plane.setX(100);
		plane.setY(100);
		
		// set up north Container
		northCont = new JPanel(new FlowLayout());
		// create and add zoom out button
		zoomout = new JButton("-");
		
		// create and add zoom in button
		zoomin = new JButton("+");
		formatNorthCont();
	//	add(northCont, BorderLayout.SOUTH);

		zoom = 1; // 0-21 disable + button is more
		zoomout.setEnabled(false);
		address = "USA";
		updateMap(address,address);
		// create menu bar
		featuresOptions = new JMenu("Features");
		menu = new JMenuBar();
		viewOptions = new JMenu("View");
		setUpMenu();
		add(menu,BorderLayout.NORTH);
	}

	public JLabel getMapLabel() {
		return this.mapImg;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		plane.drawAirplane(g);
		//g.fillRect(10, 20, 100, 100);
	}
	public void setUpMenu() {
		String[] viewNames = { "Satellite", "Roadmap", "Hybrid", "Terrain" };
		String[] featuresNames = { "Roads", "Landscape", "Transit", "transit.station.airports", "pio.school" };
		JCheckBoxMenuItem[] features = new JCheckBoxMenuItem[5];
		JMenuItem[] views = new JMenuItem[4];
		featuresOptions.setToolTipText("Features to display");
		path = "40.737102,-73.990318|40.749825,-73.987963";
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
		view = "Hybrid";
		menu.add(viewOptions);
		menu.add(zoomout);
		menu.add(zoomin);
	}

	public void formatNorthCont() {
		zoomout.addActionListener(zoomoutListen);
		northCont.add(zoomout);

		zoomin.addActionListener(zoominListen);
		northCont.add(zoomin);
	}

	public void updateFeature(String feature) throws MalformedURLException {
		this.feature = feature.toLowerCase();
		loadImg();
	}

	public void updateView(String view) throws MalformedURLException {
		this.view = view.toLowerCase();
		loadImg();
	}

	public void loadImg() throws MalformedURLException {
		new Address2Thread(address, address2, zoom, view, mapImg).start();
		/*
		 * new ImgDownloadThread(new
		 * URL("https://maps.googleapis.com/maps/api/staticmap?center=jfk&zoom="
		 * + zoom +
		 * "&size=400x400&maptype="+view+"&path=color:0x0000ff|weight:5|"
		 * +path+"&style=feature:"
		 * +feature+"%7Celement:geometry%7Cvisibility:simplified%7Ccolor:0xc280e9"
		 * ), mapImg).start();
		 */
	}

	ActionListener zoominListen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			zoom++;
			if (zoom == 21) {
				zoomin.setEnabled(false);
			}
			if (!zoomout.isEnabled()) {
				zoomout.setEnabled(true);
			}

			try {
				loadImg();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	};

	ActionListener zoomoutListen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			zoom--;
			if (zoom == 1) {
				zoomout.setEnabled(false);
			}
			if (!zoomin.isEnabled()) {
				zoomin.setEnabled(true);
			}
			try {
				loadImg();
			} catch (MalformedURLException e) {
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
				
			} catch (MalformedURLException e1) {
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
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
	};

	public void updateMap(String address, String address2) throws IOException {
		this.address=address;
		this.address2=address2;
		new Address2Thread(address,address2,view,mapImg).start();
	}
}
