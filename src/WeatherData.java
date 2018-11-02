/**
 * The top level class to be populated with weather data from openweather api as a json
 *
 * @author Duntorah
 */
class WeatherData {
    class Coord {
        double lon;
        double lat;
    }

    class Weather {
        int id;
        String main;
        String description;
        String icon;
    }

    class Main {
        double temp;
        int pressure;
        int humidity;
        double temp_min;
        double temp_max;
    }

    class Wind {
        double speed;
        int deg;
    }

    class Clouds {
        int all;
    }

    class Sys {
        int type;
        int id;
        double message;
        String country;
        int sunrise;
        int sunset;
    }

    Coord coord;
    Weather[] weather;
    String base;
    Main main;
    double visibility;
    Wind wind;
    Clouds clouds;
    int dt;
    Sys sys;
    int id;
    String name;
    int cod;
}
