package com.example.lab1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentWordCountRepository {

    @Query(value = "INSERT INTO document_word (document_id, word_id, count) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void save(Long docId, Long wordId, int count);

    @Query(value = "SELECT count FROM document_word WHERE document_id = ?1 AND word_id = ?2", nativeQuery = true)
    Integer getCount(Long documentId, Long word_id);
}
