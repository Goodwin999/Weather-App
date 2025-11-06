// Указываем пакет DTO
package com.weather.app.weatherApp.dto;

// Импортируем класс для работы с датой и временем
import java.time.LocalDateTime;

// Импортируем аннотации Swagger для документации API
import io.swagger.v3.oas.annotations.media.Schema;

// Класс для унифицированного формата ответов об ошибках
@Schema(description = "Стандартный формат ответа с ошибкой")
public class ErrorResponse {

    // Код ошибки для машинной обработки
    @Schema(
            description = "Уникальный код ошибки для програмной обработки",
            example = "CITY_NOT_FOUND"
    )
    private String code;

    // Человеко-читаемое сообщение об ошибке
    @Schema(
            description = "Человеко-читаемое сообщение об ошибке",
            example = "Город не найден"
    )
    private String message;

    // Время когда произошла ошибка
    @Schema(
            description = "Временная метка когда произошла ошибка",
            example = "2025-11-06T15:30:45.123"
    )
    private LocalDateTime timestamp;

    // Конструктор создает объект ошибки и автоматически ставит текущее время
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now(); // Время фиксируется при создании
    }

    // Пустой конструктор для Jackson
    public ErrorResponse() {
    }

    // Геттер для кода ошибки
    public String getCode() {
        return code;
    }

    // Сеттер для кода ошибки
    public void setCode(String code) {
        this.code = code;
    }

    // Геттер для сообщения об ошибке
    public String getMessage() {
        return message;
    }

    // Сеттер для сообщения об ошибке
    public void setMessage(String message) {
        this.message = message;
    }

    // Геттер для времени ошибки
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Сеттер для времени ошибки
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}