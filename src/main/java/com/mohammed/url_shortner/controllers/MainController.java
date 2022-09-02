package com.mohammed.url_shortner.controllers;

import com.mohammed.url_shortner.models.URLInfo;
import com.mohammed.url_shortner.services.URLInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/")
    public String addURL(HttpServletRequest request , @RequestParam String original_url) {
        try {
            URLInfo urlInfo = urlInfoService.createURLInfo(original_url);
        } catch (Exception exception){
            return "redirect:/error";
        }
        return "redirect:/";
    }

    @GetMapping("/{shortenUrl}")
    public void redirectToOriginalUrl(HttpServletResponse httpServletResponse, @PathVariable(required = true) String shortenUrl) {
        List<URLInfo> urlsInfo = urlInfoService.findByShortenUrl(shortenUrl);
        if (urlsInfo.isEmpty()){
            httpServletResponse.setHeader("Location", "/error");
            httpServletResponse.setStatus(302);
        } else {
            httpServletResponse.setHeader("Location", urlsInfo.get(0).getOriginalUrl());
            httpServletResponse.setStatus(302);
        }
    }

    @GetMapping("/{shortenUrl}/details")
    public String getShortenUrlDetails(@PathVariable(required = true) String shortenUrl, Model model) {
        List<URLInfo> urlsInfo = urlInfoService.findByShortenUrlDetails(shortenUrl);
        if (urlsInfo.isEmpty()){
            return "redirect:/error";
        }
        URLInfo urlInfo = urlsInfo.get(0);
        model.addAttribute("url", urlInfo);
        return "url_info";
    }

}
