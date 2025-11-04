package com.weather.app.weatherApp.exceptions;

public class CityNotFoundException extends WeatherServiceException {
    public CityNotFoundException(String city) {
        super("Город '" + city + "' не найден");
    }
}
