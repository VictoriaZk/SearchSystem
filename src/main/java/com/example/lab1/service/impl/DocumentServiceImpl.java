package com.example.lab1.service.impl;

import com.example.lab1.model.Document;
import com.example.lab1.model.Word;
import com.example.lab1.repository.DocumentRepository;
import com.example.lab1.service.DocumentService;
import com.example.lab1.service.WordService;
import com.example.lab1.utils.DocumentUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DocumentServiceImpl implements DocumentService {
    private final String PATH = System.getProperty("user.dir") + "/files";

    private final DocumentRepository documentRepository;
    private final DocumentUtils documentUtils;
    private final WordService wordService;

    @Override
    public List<Document> findAll(){
        return documentRepository.findAll();
    }

    @Override
    public Integer countAll(){
        return documentRepository.countAll();
    }

    @Override
    public Map<Word, Double> getDictionary(){
        Integer allDocumentCount = countAll();

        return wordService.findAll()
                .stream()
                .collect(Collectors.toMap(word -> word,
                        word -> Math.log(Double.valueOf(allDocumentCount)
                                / wordService.getCountDocumentsWithWord(word.getId()))));
    }

    @Override
    public void upload(MultipartFile file){
        try{
            String documentContent = new String(file.getBytes());
            Map<String, Integer> wordsCount = documentUtils.getTermOccurrences(documentContent);

            Document document = Document.builder()
                    .text(documentContent)
                    .title(file.getOriginalFilename())
                    .build();

            Document finalDocument = documentRepository.save(document);

            wordsCount.forEach((text, count) -> {
                Word word = wordService.findByText(text).orElseGet(() -> wordService.save(new Word(null, text)));
                wordService.saveWordDocumentCount(finalDocument.getId(), word.getId(), count);
            });

            saveFileStorage(file);
        }catch (IOException exception){

        }
    }

    @Override
    public String getSearchDocument(String text) {
        Map<Word, Double> dictionary = getDictionary();
        List<Double> vectorRequest = getVectorRequest(documentUtils.getTermOccurrences(text), dictionary);

        Map<Document, List<Double>> informationFlow =
                findAll().stream().collect(Collectors.toMap(document -> document,
                document -> getVectorDocument(document, dictionary)));

        Document searchResult = informationFlow
                .entrySet().stream()
                .max(Comparator.comparing(entry ->
                        documentUtils.getCoincidenceValue(vectorRequest, entry.getValue())))
                .get().getKey();

        return searchResult.getText();
    }

    private List<Double> getVectorDocument(Document document, Map<Word, Double> dictionary){
        return dictionary.entrySet().stream()
                .mapToDouble(entry ->
                        wordService.getWordCountInDocument(document.getId(), entry.getKey().getId())
                                * entry.getValue())
                .boxed().collect(Collectors.toList());
    }

    private List<Double> getVectorRequest(Map<String, Integer> wordsCount, Map<Word, Double> dictionary){
        return dictionary.entrySet().stream()
                .mapToDouble(entry ->
                        wordsCount.getOrDefault(entry.getKey().getText(), 0) * entry.getValue())
                .boxed().collect(Collectors.toList());
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
