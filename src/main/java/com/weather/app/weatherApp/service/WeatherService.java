package com.weather.app.weatherApp.service;

import com.weather.app.weatherApp.dto.WeatherDto;
import com.weather.app.weatherApp.model.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

@Service
public class WeatherService {


    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String apiKey;

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
                throw new RuntimeException("Сервис погоды вернул ошибку: " + response.getStatusCode());
            }

        } catch (HttpClientErrorException.NotFound e) {
            // Город не найден (404)
            throw new RuntimeException("Город '" + city + "' не найден", e);
        } catch (HttpClientErrorException e) {
            // Ошибка клиента (4xx)
            throw new RuntimeException("Ошибка запроса: " + e.getStatusCode(), e);
        } catch (HttpServerErrorException e) {
            // Ошибка сервера (5xx)
            throw new RuntimeException("Сервис погоды временно недоступен", e);
        } catch (UnknownHttpStatusCodeException e) {
            // Неизвестный статус код
            throw new RuntimeException("Неизвестная ошибка сервиса погоды", e);
        } catch (Exception e) {
            // Любая другая ошибка
            throw new RuntimeException("Произошла непредвиденная ошибка", e);
        }
    }

    /**
     * Получить погоду по координатам
     */
    public WeatherDto getWeatherByCoordinates(double lat, double lon) {
        try {
            String url = String.format("%s/weather?lat=%s&lon=%s&appid=%s&units=metric&lang=ru",
                    apiUrl, lat, lon, apiKey);

            ResponseEntity<WeatherApiResponse> response = restTemplate.getForEntity(
                    url, WeatherApiResponse.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return mapToDto(response.getBody());
            } else {
                throw new RuntimeException("Сервис погоды вернул ошибку");
            }

        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения погоды по координатам", e);
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

        return new WeatherDto(
                apiResponse.getName() != null ? apiResponse.getName() : "Неизвестно",
                apiResponse.getMain() != null ? apiResponse.getMain().getTemp() : 0,
                description,
                apiResponse.getMain() != null ? apiResponse.getMain().getHumidity() : 0,
                apiResponse.getWind() != null ? apiResponse.getWind().getSpeed() : 0
        );
    }
}