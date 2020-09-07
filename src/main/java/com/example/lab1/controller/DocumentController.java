package com.example.lab1.controller;

import com.example.lab1.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping(value = "/document")
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping("/upload")
    public String upload(@RequestParam("document") List<MultipartFile> file) {
        documentService.upload(file);
        return "redirect:/";
    }
}
