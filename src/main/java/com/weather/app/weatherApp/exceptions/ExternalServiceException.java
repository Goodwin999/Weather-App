package com.weather.app.weatherApp.exceptions;

public class ExternalServiceException extends WeatherServiceException {
    public ExternalServiceException(String serviceName) {
        super("Сервис " + serviceName + " временно недоступен");
    }
}
