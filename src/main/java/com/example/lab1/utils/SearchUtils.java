package com.example.lab1.utils;

import com.example.lab1.repository.DocumentRepository;
import com.example.lab1.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SearchUtils {
    private final DocumentRepository documentRepository;
    private final WordRepository wordRepository;


}
