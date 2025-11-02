package com.weather.app.weatherApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные о погоде")
public class WeatherDto {

    @Schema(description = "Название города", example = "Москва")
    private String name;

    @Schema(description = "Температура в градусах Цельсия", example = "15.5")
    private double temp;

    @Schema(description = "Описание погоды", example = "ясно")
    private String description;

    @Schema(description = "Влажность в процентах", example = "65")
    private int humidity;

    @Schema(description = "Скорость ветра м/с", example = "3.2")
    private double windSpeed;

    public WeatherDto() {
        // пустой конструктор для Jackson
    }
    public WeatherDto(String name, double temp, String description, int humidity, double windSpeed) {
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