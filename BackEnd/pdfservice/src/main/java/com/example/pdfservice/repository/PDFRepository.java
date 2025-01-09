package com.example.pdfservice.repository;

import com.example.pdfservice.model.PDFEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PDFRepository extends JpaRepository<PDFEntity, Long> {
}
