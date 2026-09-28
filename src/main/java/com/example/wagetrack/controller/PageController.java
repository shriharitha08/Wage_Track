package com.example.wagetrack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/workers")
    public String workers() {
        return "workers";
    }

    @GetMapping("/worksites")
    public String worksites() {
        return "worksites";
    }

    @GetMapping("/attendance")
    public String attendance() {
        return "attendance";
    }

    @GetMapping("/wages")
    public String wages() {
        return "wages";
    }

    @GetMapping("/payments")
    public String payments() {
        return "payments";
    }
}