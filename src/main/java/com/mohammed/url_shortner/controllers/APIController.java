package com.mohammed.url_shortner.controllers;

import com.mohammed.url_shortner.models.URLInfo;
import com.mohammed.url_shortner.services.URLInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("api")
public class APIController {

    private final URLInfoService urlInfoService;

    @Autowired
    public APIController(URLInfoService urlInfoService) {
        this.urlInfoService = urlInfoService;
    }

    @GetMapping("/urls")
    public List<URLInfo> getAll() {
        return urlInfoService.findAll();
    }

}
