package com.weather.app.weatherApp.controller;

import com.weather.app.weatherApp.dto.WeatherDto;
import com.weather.app.weatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherViewController {

    @Autowired
    private WeatherService weatherService;

    /**
     * Главная страница с формой
     */
    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("city", "");
        return "index";
    }

    /**
     * Обработка формы поиска погоды
     */
    @PostMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) {
        try {
            WeatherDto weather = weatherService.getWeather(city);
            model.addAttribute("weather", weather);
            model.addAttribute("success", true);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("success", false);
        }

        model.addAttribute("city", city);
        return "index";
    }

    /**
     * Страница "О проекте"
     */
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}