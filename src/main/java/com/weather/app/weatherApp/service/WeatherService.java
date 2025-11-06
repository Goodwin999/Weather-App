package com.weather.app.weatherApp.service;

import com.weather.app.weatherApp.dto.WeatherDto;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    public WeatherDto getWeather(String city) {
        // Временная заглушка
        return new WeatherDto(city, 15.5, "Солнечно", 65, 3.2);
    }

    public WeatherDto getWeatherByCoordinates(double lat, double lon) {
        return new WeatherDto("Тестовый город", 18.0, "Облачно", 70, 2.5);
    }
}