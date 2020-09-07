package com.example.lab1.utils;

import com.example.lab1.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DocumentUtils {
    private final DocumentService documentService;
}
