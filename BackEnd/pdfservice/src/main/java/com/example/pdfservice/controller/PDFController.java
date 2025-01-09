package com.example.pdfservice.controller;

import com.example.pdfservice.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdf")
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<String> generatePDF(@RequestBody String content) {
        String filePath = pdfService.generatePDF(content);
        return ResponseEntity.ok("PDF generated at: " + filePath);
    }
}
