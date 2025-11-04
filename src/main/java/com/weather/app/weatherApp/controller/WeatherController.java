package com.weather.app.weatherApp.controller;

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

// controller/WeatherController.java
@RestController
@RequestMapping("/api/v1/weather")
@Tag(name = "Weather API", description = "API для получения данных о погоде")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Operation(
            summary = "Получить погоду по городу",
            description = "Возвращает текущую погоду для указанного города"
    )
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
    @GetMapping("/{city}")
    public ResponseEntity<WeatherDto> getWeather(
            @Parameter(description = "Название города на русском или английском", example = "Москва")
            @PathVariable String city
    ) {
        WeatherDto weather = weatherService.getWeather(city);
        return ResponseEntity.ok(weather);
    }

    @Operation(summary = "Поиск погоды по координатам")
    @GetMapping("/coordinates")
    public ResponseEntity<WeatherDto> getWeatherByCoordinates(
            @Parameter(description = "Географическая широта") @RequestParam double lat,
            @Parameter(description = "Географическая долгота") @RequestParam double lon
    ) {
        WeatherDto weather = weatherService.getWeatherByCoordinates(lat, lon);
        return ResponseEntity.ok(weather);
    }
}