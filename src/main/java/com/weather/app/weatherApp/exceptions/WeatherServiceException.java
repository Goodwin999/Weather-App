// Указываем пакет исключений
package com.weather.app.weatherApp.exceptions;

// Базовое исключение для всех ошибок связанных с сервисом погоды
public class WeatherServiceException extends RuntimeException {
    // Конструктор принимает сообщение об ошибке
    public WeatherServiceException(String message) {
        super(message); // Передаем сообщение в родительский класс
    }
}