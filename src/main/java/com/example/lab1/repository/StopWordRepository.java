package com.example.lab1.repository;

import com.example.lab1.model.StopWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopWordRepository extends JpaRepository<StopWord, Long> {
}
