package airplane;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class WeatherDownloadThread extends Thread {
	private WeatherInfo box;
	private URL url;

	public WeatherDownloadThread(WeatherInfo box, String lat, String log) throws MalformedURLException {
		this.box = box;
		url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + lat + "," + log + "&units=imperial");
	}

	public WeatherDownloadThread(WeatherInfo box, String address) throws MalformedURLException {
		this.box = box;
		url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + address + "&units=imperial");
	}

	@Override
	public void run() {
		try {
			// retrieve weather data from online
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			String json = IOUtils.toString(in);

			// convert json file into readable gson file, now accessible classes
			Gson gson = new Gson();
			WeatherNow now = gson.fromJson(json, WeatherNow.class);

			box.displayWeather(now);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
