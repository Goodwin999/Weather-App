package com.weather.app.weatherApp.service;

import com.weather.app.weatherApp.dto.WeatherDto;
import com.weather.app.weatherApp.exceptions.CityNotFoundException;
import com.weather.app.weatherApp.exceptions.ExternalServiceException;
import com.weather.app.weatherApp.exceptions.WeatherServiceException;
import com.weather.app.weatherApp.model.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    // Внедряем RestTemplate через конструктор
    private final RestTemplate restTemplate;
    // Значения из application.properties
    private final String apiUrl;
    private final String apiKey;

    // Конструктор для внедрения зависимостей
    public WeatherService(RestTemplate restTemplate,
                          @Value("${weather.api.url}") String apiUrl,
                          @Value("${weather.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    /**
     * Получить погоду по названию города
     */
    public WeatherDto getWeather(String city) {
        try {
            // Формируем URL для запроса
            String url = String.format("%s/weather?q=%s&appid=%s&units=metric&lang=ru",
                    apiUrl, city, apiKey);

            // Выполняем запрос к OpenWeather API
            ResponseEntity<WeatherApiResponse> response = restTemplate.getForEntity(
                    url, WeatherApiResponse.class);

            // Проверяем успешность ответа
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return mapToDto(response.getBody());
            } else {
                // Если статус не 2xx, бросаем исключение о недоступности сервиса
                throw new ExternalServiceException("OpenWeather");
            }

        } catch (HttpClientErrorException.NotFound e) {
            // Город не найден (404) - бросаем кастомное исключение
            throw new CityNotFoundException(city);
        } catch (HttpClientErrorException e) {
            // Ошибка клиента (4xx) - сервис недоступен
            throw new ExternalServiceException("OpenWeather");
        } catch (HttpServerErrorException e) {
            // Ошибка сервера (5xx) - сервис недоступен
            throw new ExternalServiceException("OpenWeather");
        } catch (Exception e) {
            // Любая другая ошибка - общее исключение сервиса
            throw new WeatherServiceException("Произошла непредвиденная ошибка");
        }
    }

    /**
     * Получить погоду по координатам
     */
    public WeatherDto getWeatherByCoordinates(double lat, double lon) {
        try {
            // Формируем URL для запроса по координатам
            String url = String.format("%s/weather?lat=%s&lon=%s&appid=%s&units=metric&lang=ru",
                    apiUrl, lat, lon, apiKey);

            // Выполняем запрос к OpenWeather API
            ResponseEntity<WeatherApiResponse> response = restTemplate.getForEntity(
                    url, WeatherApiResponse.class);

            // Проверяем успешность ответа
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return mapToDto(response.getBody());
            } else {
                // Если статус не 2xx, бросаем исключение о недоступности сервиса
                throw new ExternalServiceException("OpenWeather");
            }

        } catch (Exception e) {
            // Любая ошибка при запросе по координатам - сервис недоступен
            throw new ExternalServiceException("OpenWeather");
        }
    }

    /**
     * Преобразование API response в DTO
     */
    private WeatherDto mapToDto(WeatherApiResponse apiResponse) {
        // Безопасное извлечение описания погоды
        String description = "нет данных";
        if (apiResponse.getWeather() != null && !apiResponse.getWeather().isEmpty()) {
            description = apiResponse.getWeather().getFirst().getDescription();
        }

        // Создаем DTO с безопасными значениями по умолчанию
        return new WeatherDto(
                apiResponse.getName() != null ? apiResponse.getName() : "Неизвестно",
                apiResponse.getMain() != null ? apiResponse.getMain().getTemp() : 0,
                description,
                apiResponse.getMain() != null ? apiResponse.getMain().getHumidity() : 0,
                apiResponse.getWind() != null ? apiResponse.getWind().getSpeed() : 0
        );
    }
}