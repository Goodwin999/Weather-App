package com.weather.app.weatherApp.dto;

// Импортируем аннотацию Swagger для документации
import io.swagger.v3.oas.annotations.media.Schema;

// Аннотация Swagger - описывает весь класс в документации
@Schema(description = "Данные о погоде")
public class WeatherDto {

    // Аннотация Swagger - описание поля для документации + пример значения
    @Schema(description = "Название города", example = "Москва")
    private String name;  // Поле для хранения названия города

    // Аннотация Swagger - описание поля + пример значения
    @Schema(description = "Температура в градусах Цельсия", example = "15.5")
    private double temp;  // Поле для хранения температуры

    // Аннотация Swagger - описание поля + пример значения
    @Schema(description = "Описание погоды", example = "ясно")
    private String description;  // Поле для хранения описания погоды

    // Аннотация Swagger - описание поля + пример значения
    @Schema(description = "Влажность в процентах", example = "65")
    private int humidity;  // Поле для хранения влажности

    // Аннотация Swagger - описание поля + пример значения
    @Schema(description = "Скорость ветра м/с", example = "3.2")
    private double windSpeed;  // Поле для хранения скорости ветра

    // Пустой конструктор - ОБЯЗАТЕЛЕН для Jackson (библиотеки для работы с JSON)
    public WeatherDto() {
        // пустой конструктор для Jackson
    }

    // Конструктор со всеми параметрами - для удобного создания объектов
    public WeatherDto(String name, double temp, String description, int humidity, double windSpeed) {
        this.name = name;           // Устанавливаем название города
        this.temp = temp;           // Устанавливаем температуру
        this.description = description;  // Устанавливаем описание погоды
        this.humidity = humidity;   // Устанавливаем влажность
        this.windSpeed = windSpeed; // Устанавливаем скорость ветра
    }

    // Геттер для названия города - возвращает значение поля
    public String getName() {
        return name;
    }

    // Сеттер для названия города - устанавливает значение поля
    public void setName(String name) {
        this.name = name;
    }

    // Геттер для температуры - возвращает значение поля
    public double getTemp() {
        return temp;
    }

    // Сеттер для температуры - устанавливает значение поля
    public void setTemp(double temp) {
        this.temp = temp;
    }

    // Геттер для описания погоды - возвращает значение поля
    public String getDescription() {
        return description;
    }

    // Сеттер для описания погоды - устанавливает значение поля
    public void setDescription(String description) {
        this.description = description;
    }

    // Геттер для влажности - возвращает значение поля
    public int getHumidity() {
        return humidity;
    }

    // Сеттер для влажности - устанавливает значение поля
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    // Геттер для скорости ветра - возвращает значение поля
    public double getWindSpeed() {
        return windSpeed;
    }

    // Сеттер для скорости ветра - устанавливает значение поля
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}