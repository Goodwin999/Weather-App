// Указываем пакет обработчика исключений
package com.weather.app.weatherApp.controller;

// Импорты наших классов и Spring компонентов
import com.weather.app.weatherApp.dto.ErrorResponse;
import com.weather.app.weatherApp.exceptions.CityNotFoundException;
import com.weather.app.weatherApp.exceptions.ExternalServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Глобальный обработчик исключений для всех REST контроллеров
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Обрабатывает исключение "Город не найден"
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCityNotFound(CityNotFoundException ex) {
        // Создаем объект ошибки с кодом и сообщением
        ErrorResponse error = new ErrorResponse("CITY_NOT_FOUND", ex.getMessage());
        // Возвращаем HTTP 404 с информацией об ошибке
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Обрабатывает исключение "Внешний сервис недоступен"
    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ErrorResponse> handleExternalService(ExternalServiceException ex) {
        // Создаем объект ошибки с кодом и сообщением
        ErrorResponse error = new ErrorResponse("EXTERNAL_SERVICE_ERROR", ex.getMessage());
        // Возвращаем HTTP 503 с информацией об ошибке
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    // Обрабатывает все остальные непредвиденные исключения
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        // Создаем общий объект ошибки (не показываем детали пользователю)
        ErrorResponse error = new ErrorResponse("INTERNAL_ERROR", "Внутренняя ошибка сервера");
        // Возвращаем HTTP 500 с общей информацией об ошибке
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}