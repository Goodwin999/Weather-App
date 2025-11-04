// Указываем пакет DTO
package com.weather.app.weatherApp.dto;

// Импортируем класс для работы с датой и временем
import java.time.LocalDateTime;

// Класс для унифицированного формата ответов об ошибках
public class ErrorResponse {

    // Код ошибки для машинной обработки
    private String code;
    // Человеко-читаемое сообщение об ошибке
    private String message;
    // Время когда произошла ошибка
    private LocalDateTime timestamp;

    // Конструктор создает объект ошибки и автоматически ставит текущее время
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now(); // Время фиксируется при создании
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