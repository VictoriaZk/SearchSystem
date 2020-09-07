package com.example.lab1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping(value = "/search")
public class SearchController {
    @GetMapping("/main")
    public String getStartPage(Model model) {
        return "start";
    }
}
