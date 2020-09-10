package com.example.lab1.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    void upload(MultipartFile file);


}
