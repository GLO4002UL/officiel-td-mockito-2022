package ca.ulaval.glo4002.part3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeatherClientTest {
    private static final String WEATHER_MESSAGE = "30;5;320";
    private static final int TEMPERATURE = 30;
    private static final int WIND_SPEED = 5;
    private static final int WIND_DIRECTION = 320;

    private TestableWeatherClient weatherClient;

    @BeforeEach
    void before() {
        weatherClient = new TestableWeatherClient(WEATHER_MESSAGE);
    }

    @Test
    void givenWebsiteResponds_whenFetchingWeather_thenShouldReturnWeather() {
        Weather weather = weatherClient.fetchWeather();

        assertEquals(TEMPERATURE, weather.temperature());
        assertEquals(WIND_SPEED, weather.windSpeed());
        assertEquals(WIND_DIRECTION, weather.windDirection());
    }

    @Test
    void givenWebsiteIsDown_whenFetchingWeather_thenShouldThrowException() {
        weatherClient.simulateWebsiteDown();

        Executable call = () -> weatherClient.fetchWeather();

        assertThrows(WeatherUnavailableException.class, call);
    }
}

class TestableWeatherClient extends WeatherClient {
    private String weatherMessage;

    public TestableWeatherClient(String weatherMessage) {
        super();
        this.weatherMessage = weatherMessage;
    }

    public void simulateWebsiteDown() {
        this.weatherMessage = "";
    }

    @Override
    protected String getWeatherMessage() {
        return weatherMessage;
    }
}
