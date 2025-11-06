package com.weather.app.weatherApp.model;

import java.util.List;

/**
 * Класс для парсинга JSON ответа от OpenWeather API
 * Соответствует структуре JSON от внешнего сервиса
 */
public class WeatherApiResponse {
    // Название города из JSON ответа
    private String name;

    // Основные данные о погоде (температура, влажность) - вложенный объект
    private Main main;

    // Список погодных условий (может быть несколько)
    private List<Weather> weather;

    // Данные о ветре - вложенный объект
    private Wind wind;

    // Вложенный класс для основных погодных данных
    public static class Main {
        // Температура в Кельвинах (нужно конвертировать в Цельсии)
        private double temp;

        // Влажность в процентах
        private int humidity;

        // Геттер температуры
        public double getTemp() {
            return temp;
        }

        // Сеттер температуры
        public void setTemp(double temp) {
            this.temp = temp;
        }

        // Геттер влажности
        public int getHumidity() {
            return humidity;
        }

        // Сеттер влажности
        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }

    // Вложенный класс для описания погодных условий
    public static class Weather {
        // Текстовое описание погоды (например, "ясно", "дождь")
        private String description;

        // Геттер описания погоды
        public String getDescription() {
            return description;
        }

        // Сеттер описания погоды
        public void setDescription(String description) {
            this.description = description;
        }
    }

    // Вложенный класс для данных о ветре
    public static class Wind {
        // Скорость ветра в метрах в секунду
        private double speed;

        // Геттер скорости ветра
        public double getSpeed() {
            return speed;
        }

        // Сеттер скорости ветра
        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }

    // Геттер названия города
    public String getName() {
        return name;
    }

    // Сеттер названия города
    public void setName(String name) {
        this.name = name;
    }

    // Геттер основных погодных данных
    public Main getMain() {
        return main;
    }

    // Сеттер основных погодных данных
    public void setMain(Main main) {
        this.main = main;
    }

    // Геттер списка погодных условий
    public List<Weather> getWeather() {
        return weather;
    }

    // Сеттер списка погодных условий
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    // Геттер данных о ветре
    public Wind getWind() {
        return wind;
    }

    // Сеттер данных о ветре
    public void setWind(Wind wind) {
        this.wind = wind;
    }
}