// Указываем пакет контроллера
package com.weather.app.weatherApp.controller;

// Импорты наших классов и Spring компонентов
import com.weather.app.weatherApp.dto.ErrorResponse;
import com.weather.app.weatherApp.dto.WeatherDto;
import com.weather.app.weatherApp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Помечаем класс как REST контроллер (возвращает JSON, а не HTML)
@RestController
// Базовый путь для всех методов этого контроллера
@RequestMapping("/api/v1/weather")
// Аннотация Swagger - название и описание API в документации
@Tag(name = "Weather API", description = "API для получения данных о погоде")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Swagger аннотация - описание операции
    @Operation(
            summary = "Получить погоду по городу",
            description = "Возвращает текущую погоду для указанного города"
    )
    // Swagger аннотация - возможные ответы API
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = WeatherDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Город не найден",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Сервис погоды недоступен",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    // Обрабатывает GET запросы по пути /api/v1/weather/{город}
    @GetMapping("/{city}")
    public ResponseEntity<WeatherDto> getWeather(
            // Swagger аннотация - описание параметра пути
            @Parameter(description = "Название города на русском или английском", example = "Москва")
            @PathVariable String city  // Извлекает значение {city} из URL
    ) {
        // Вызываем сервис для получения данных о погоде
        WeatherDto weather = weatherService.getWeather(city);
        // Возвращаем успешный ответ с данными
        return ResponseEntity.ok(weather);
    }

    // Swagger аннотация - краткое описание операции
    @Operation(summary = "Поиск погоды по координатам")
    // Обрабатывает GET запросы по пути /api/v1/weather/coordinates?lat=...&lon=...
    @GetMapping("/coordinates")
    public ResponseEntity<WeatherDto> getWeatherByCoordinates(
            // Swagger аннотация - описание параметра запроса
            @Parameter(description = "Географическая широта") @RequestParam double lat,
            // Swagger аннотация - описание параметра запроса
            @Parameter(description = "Географическая долгота") @RequestParam double lon
    ) {
        // Вызываем сервис для получения данных по координатам
        WeatherDto weather = weatherService.getWeatherByCoordinates(lat, lon);
        // Возвращаем успешный ответ с данными
        return ResponseEntity.ok(weather);
    }
}