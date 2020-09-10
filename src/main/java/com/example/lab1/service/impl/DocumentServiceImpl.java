package com.example.lab1.service.impl;

import com.example.lab1.model.Document;
import com.example.lab1.model.Word;
import com.example.lab1.repository.DocumentRepository;
import com.example.lab1.repository.DocumentWordCountRepository;
import com.example.lab1.service.DocumentService;
import com.example.lab1.service.WordService;
import com.example.lab1.utils.DocumentUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DocumentServiceImpl implements DocumentService {
    private final String PATH = System.getProperty("user.dir") + "/files";

    private final DocumentRepository documentRepository;
    private final DocumentUtils documentUtils;
    private final WordService wordService;
    private final DocumentWordCountRepository documentWordCountRepository;

    @Override
    public void upload(MultipartFile file){
        try{
            String documentContent = new String(file.getBytes());
            HashMap<String, Integer> wordsCount = documentUtils.getTermOccurrences(documentContent);

            Document document = new Document();
            document.setTitle(file.getOriginalFilename());
            document.setText(documentContent);
            Document finalDocument = documentRepository.save(document);

            wordsCount.forEach((text, count) -> {
                Word word = wordService.findByText(text).orElseGet(() -> wordService.save(new Word(null, text)));
                documentWordCountRepository.save(finalDocument.getId(), word.getId(), count);
            });

            saveFileStorage(file);
        }catch (IOException exception){

        }

    }

    private void saveFileStorage(MultipartFile file){
        File fileToSave = new File(PATH + '/' + file.getOriginalFilename());
        try {
            if (!fileToSave.exists()) {
                fileToSave.createNewFile();
            }

            file.transferTo(fileToSave);
        } catch (IOException e) {
            fileToSave.delete();
        }
    }

}
