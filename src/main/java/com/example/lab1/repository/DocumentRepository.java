package com.example.lab1.repository;

import com.example.lab1.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("SELECT d FROM Document d WHERE d.text in ?1")
    List<Document> findAllByTextContains(String text);

    Document save(Document document);

    @Query("SELECT COUNT (d) FROM Document d")
    Integer countAll();

    @Query("SELECT d FROM Document d")
    List<Document> findAll();



}
