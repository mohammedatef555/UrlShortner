package com.mohammed.url_shortner.controllers;

import com.mohammed.url_shortner.models.URLInfo;
import com.mohammed.url_shortner.services.URLInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MainController {

    private final URLInfoService urlInfoService;

    @Autowired
    public MainController(URLInfoService urlInfoService) {
        this.urlInfoService = urlInfoService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<URLInfo> all_urls = urlInfoService.findAll();
        model.addAttribute("urls", all_urls);
        return "home";
    }

    @PostMapping("/")
    public String addURL(HttpServletRequest request , @RequestParam String original_url) {
        URLInfo urlInfo = urlInfoService.createURLInfo(original_url);
        return "redirect:/";
    }

    @GetMapping("/{shortenUrl}")
    public void method(HttpServletResponse httpServletResponse, @PathVariable(required = true) String shortenUrl) {
        List<URLInfo> urlsInfo = urlInfoService.findByShortenUrl(shortenUrl);
        if (urlsInfo.isEmpty()){
            httpServletResponse.setHeader("Location", "/error");
            httpServletResponse.setStatus(302);
        } else {
            System.out.println(urlsInfo);
            System.out.println(urlsInfo.isEmpty());
            httpServletResponse.setHeader("Location", urlsInfo.get(0).getOriginalUrl());
            httpServletResponse.setStatus(302);
        }
    }

}
