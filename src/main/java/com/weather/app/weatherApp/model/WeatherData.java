package com.weather.app.weatherApp.model;

public class WeatherData {

    private String name;          // город
    private double temp;          // температура
    private String description;   // описание погоды
    private int humidity;         // влажность
    private double windSpeed;     // скорость ветра

    public WeatherData() {
        // пустой конструктор для Jackson
    }
    public WeatherData(String name, double temp, String description, int humidity, double windSpeed) {
        this.name = name;
        this.temp = temp;
        this.description = description;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

}