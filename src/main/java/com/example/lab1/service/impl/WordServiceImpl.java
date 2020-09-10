package com.example.lab1.service.impl;

import com.example.lab1.model.Word;
import com.example.lab1.repository.WordRepository;
import com.example.lab1.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    @Override
    public Optional<Word> findByText(String text){
        return wordRepository.findByText(text);
    }

    @Override
    public Word save(Word word){
        return wordRepository.save(word);
    }

}
