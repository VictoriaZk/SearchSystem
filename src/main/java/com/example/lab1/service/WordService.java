package com.example.lab1.service;

import com.example.lab1.model.Word;

import java.util.Optional;

public interface WordService {
    Optional<Word> findByText(String text);

    Word save(Word word);

}
