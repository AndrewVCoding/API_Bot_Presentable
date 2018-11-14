import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class APIclass {
    private String APP_ID;
    private FetchWeather FETCH_WEATHER;
    private ISS ISS_DATA;

    APIclass() {
        APP_ID = "9146a1be7f62f3737023afe868fbbdf0";
    }

    /**
     * fetches data from openWeather using the Gson library and the provided zipcode
     *
     * @param zipcode The zip code location of requested weather
     * @throws IOException
     */
    void fetchWeather(String zipcode) throws IOException {

        // get the url for the weather data object
        String address = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipcode + ",us&appid=" + APP_ID;
        URL url = new URL(address);

        // create an input stream from the url
        InputStreamReader reader = new InputStreamReader(url.openStream());

        // Fetch the weather data
        FETCH_WEATHER = new FetchWeather(reader);
    }

    /**
     * Retrieves the position of the ISS
     *
     * @throws IOException
     */
    void fetchISS() throws IOException {

        // Get the urk for the ISS position
        URL url = new URL("http://api.open-notify.org/iss-now.json");

        // create an input stream from the url
        InputStreamReader reader = new InputStreamReader(url.openStream());

        // create a gson builder for the weather data
        Gson gson = new GsonBuilder().create();

        ISS_DATA = gson.fromJson(reader, ISS.class);
    }

    /**
     * Retrieve unit correct temperature
     *
     * @param units
     * @return
     */
    String getTemp(String units) {
        return FETCH_WEATHER.getTemp(units);
    }

    /**
     * Retrieve unit correct description of the weather
     */
    String getWeather(String units) {
        return FETCH_WEATHER.getWeather(units);
    }

    String getPos() {
        return "LATITUDE: " + ISS_DATA.iss_position.latitude + "    LONGITUDE: " + ISS_DATA.iss_position.longitude;
    }
}
