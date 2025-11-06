// Указываем пакет исключений
package com.weather.app.weatherApp.exceptions;

// Исключение "Город не найден" - частный случай WeatherServiceException
public class CityNotFoundException extends WeatherServiceException {
    // Конструктор принимает название города и формирует сообщение
    public CityNotFoundException(String city) {
        super("Город '" + city + "' не найден");
    }
}