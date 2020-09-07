package com.example.lab1.service.impl;

import com.example.lab1.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DocumentServiceImpl implements DocumentService {
    private final String PATH = System.getProperty("user.dir") + "/files";

    @Override
    public void upload(List<MultipartFile> files) {
        files.forEach(file -> {
            try {
                File fileToSave = new File(PATH + '/' + file.getOriginalFilename());
                if (!fileToSave.exists()) {
                    fileToSave.createNewFile();
                }
                file.transferTo(fileToSave);
            } catch (IOException e) {
                System.out.println("lalalalala");
            }
        });
    }
}
