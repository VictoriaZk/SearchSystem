package com.example.lab1.service;

import com.example.lab1.model.Document;
import com.example.lab1.model.Word;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DocumentService {
    void upload(MultipartFile file);

    String getSearchDocument(String text);

    Map<Word, Double> getDictionary();

    List<Document> findAll();

    Integer countAll();


}
