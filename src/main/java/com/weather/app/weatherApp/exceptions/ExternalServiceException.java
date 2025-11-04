// Указываем пакет исключений  
package com.weather.app.weatherApp.exceptions;

// Исключение "Внешний сервис недоступен" - частный случай WeatherServiceException
public class ExternalServiceException extends WeatherServiceException {
    // Конструктор принимает имя сервиса и формирует сообщение
    public ExternalServiceException(String serviceName) {
        super("Сервис " + serviceName + " временно недоступен");
    }
}