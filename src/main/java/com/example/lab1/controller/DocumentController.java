package com.example.lab1.controller;

import com.example.lab1.service.DocumentService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Morph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping(value = "/document")
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/upload")
    public String upload(@RequestParam(value = "fileToUpload") MultipartFile file) {
        documentService.upload(file);
        return "redirect:/";
    }

    @GetMapping("/upload")
    public String getUploadPage() {
        return "upload";
    }
}
