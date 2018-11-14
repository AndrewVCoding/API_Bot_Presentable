import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStreamReader;

class FetchWeather {
    private WeatherData WEATHER_DATA;

    FetchWeather(InputStreamReader reader) {
        Gson gson = new GsonBuilder().create();

        WEATHER_DATA = gson.fromJson(reader, WeatherData.class);
    }

    /**
     * Returns the temperature given either 'C' or 'F'
     * "XX.XX degrees <farenheit/celsius>
     *
     * @param units Imperial/Metric
     * @return The temperature
     */
    String getTemp(String units) {
        String degrees = "";
        double temp = WEATHER_DATA.main.temp;

        // if farenheit
        if (units.equalsIgnoreCase("I")) {
            degrees = "degrees farenheit";
            temp = ((WEATHER_DATA.main.temp * 9) / 5) - 459.67;
        }
        // if celsius
        if (units.equalsIgnoreCase("M")) {
            degrees = "degrees celsius";
            temp = WEATHER_DATA.main.temp - 273.15;
        }

        return String.format("%.2f " + degrees, temp);
    }

    /**
     * Returns a broad description of the current weather
     * <p>
     * Current weather for <name> on <date>: <description> and <temp>, with a high of <temp_high>,a
     * low of <temp_low> and humidity of <humidity>%
     *
     * @param units Imperial/Metric
     * @return A short description of the weather
     */
    String getWeather(String units) {
        String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(WEATHER_DATA.dt * 1000));

        // Put all the information together
        String description = "Current weather for " + WEATHER_DATA.name + " on " + date + ": " + WEATHER_DATA.weather[0].description + " and " + getTemp(units)
                + ", with a humidity of " + WEATHER_DATA.main.humidity + "%";

        return description;
    }
}
