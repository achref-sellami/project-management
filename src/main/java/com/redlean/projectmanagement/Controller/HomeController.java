package com.redlean.projectmanagement.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/")
    public String Index() {
        return "Hello there ! I am running.";
    }
}
