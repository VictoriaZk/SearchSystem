package com.example.lab1.repository;

import com.example.lab1.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {
    @Query("SELECT w FROM Word w WHERE w.text = ?1")
    Optional<Word> findByText(String text);
}

