package com.example.lab1.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    void upload(List<MultipartFile> file);

}
