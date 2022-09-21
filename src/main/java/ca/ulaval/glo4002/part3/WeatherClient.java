package ca.ulaval.glo4002.part3;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WeatherClient {
    private static final String SERVICE_URL = "http://my-weather-service.com/city/weather";

    public Weather fetchWeather() {
        String message;
        try (Scanner scanner = new Scanner(new URL(SERVICE_URL).openStream(), StandardCharsets.UTF_8))
        {
            scanner.useDelimiter("\\A");
            message = scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (message == null || message.length() == 0) {
            throw new WeatherUnavailableException();
        }

        // content format is : temperate; windSpeed; windDirection
        String[] messageParts = message.split(";");
        return new Weather(
                Integer.parseInt(messageParts[0]),
                Integer.parseInt(messageParts[1]),
                Integer.parseInt(messageParts[2])
        );
    }
}
