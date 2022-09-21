package ca.ulaval.glo4002.part3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class WeatherClientTest {

    private WeatherClient weatherClient;

    @BeforeEach
    void before() {
        weatherClient = new WeatherClient();
    }

    @Test
    void givenWebsiteResponds_whenFetchingWeather_thenShouldReturnWeather() {
        Weather weather = weatherClient.fetchWeather();

        // weather.temperate = ??
        // weather.windSpeed = ??
        // weather.windDirection = ??
    }

    @Test
    void givenWebsiteIsDown_whenFetchingWeather_thenShouldThrowException() {
        // ??

        Executable call = () -> weatherClient.fetchWeather();

        assertThrows(WeatherUnavailableException.class, call);
    }

}
