package airplane;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class Address2Thread extends Thread {
	private JLabel label;
	private String address;
	private String lat;
	private String log;
	private String path;
	private String lat2;
	private String log2;
	private String address2;
	private String view;
	private URL url;
	private int zoom;

	public Address2Thread(String address, String address2, String view, JLabel label) throws IOException {
		this.address = address;
		this.address2 = address2;
		this.label = label;
		this.view = view;
		zoom=0;
	}

	public Address2Thread(String address, String address2,int zoom, String view, JLabel mapImg) {
		this.address = address;
		this.address2 = address2;
		this.label = mapImg;
		this.view = view;
		this.zoom=zoom;
		
	}

	@Override
	public void run() {
		// setCoords(address,address2);
		Gson gson = new Gson();
		try {
			url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address
					+ "&key=AIzaSyAirHEsA08agmW9uizDvXagTjWS3mRctPE");
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			String json = IOUtils.toString(in);
			AdrResults info = gson.fromJson(json, AdrResults.class);
			AdrResult[] results = info.getResults();

			for (AdrResult i : results) {
				AdrLocation location = i.getGeometry().getLocation();
				lat = location.getLat();
				log = location.getLng();
			}

			url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address2
					+ "&key=AIzaSyAirHEsA08agmW9uizDvXagTjWS3mRctPE");
			connection = url.openConnection();
			in = connection.getInputStream();
			json = IOUtils.toString(in);
			info = gson.fromJson(json, AdrResults.class);
			results = info.getResults();

			for (AdrResult i : results) {
				AdrLocation location = i.getGeometry().getLocation();
				lat2 = location.getLat();
				log2 = location.getLng();
			}
			setPath();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		// side map size is always 300x600
		try {
			String zooms="";
			if(zoom!=0){
				zooms="&zoom="+zoom;
			}
			url = new URL("https://maps.googleapis.com/maps/api/staticmap?size=300x600&path=color:0x0000ff|weight:5|"
					+ path + "&maptype=" + view + "&markers=size:mid%7Ccolor:red%7C" + address2 + "%7C" + address+zooms);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}

		ImgDownloadThread thread = new ImgDownloadThread(url, label);
		thread.start();
	}

	public void setPath() throws MalformedURLException {
		StringBuilder builder = new StringBuilder();
		builder.append(lat + "," + log + "|" + lat2 + "," + log2);
		path = builder.toString();
	}
}