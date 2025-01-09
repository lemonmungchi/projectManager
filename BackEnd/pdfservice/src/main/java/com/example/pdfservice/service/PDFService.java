package com.example.pdfservice.service;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

@Service
public class PDFService {

    // PDF 생성
    public File createPdf(String dirPath, String fileName) throws Exception {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!dir.isDirectory()) {
            throw new Exception("Invalid directory path");
        }

        File pdfFile = new File(dirPath + File.separator + fileName + ".pdf");
        if (pdfFile.exists()) {
            pdfFile.delete();
        }

        try (PDDocument document = new PDDocument()) {
            document.save(pdfFile);
        }

        return pdfFile;
    }

    // 이미지 파일을 PDF로 변환
    public void imagesToPdf(String sourceDir, String targetDir, String pdfFileName) throws Exception {
        File dir = new File(sourceDir);

        File[] imageFiles = dir.listFiles(pathname -> pathname.getName().endsWith("jpg") || pathname.getName().endsWith("png"));

        File pdfFile = createPdf(targetDir, pdfFileName);

        try (PDDocument document = new PDDocument()) {
            for (File imageFile : imageFiles) {
                Image image = ImageIO.read(imageFile);

                float width = image.getWidth(null);
                float height = image.getHeight(null);

                PDRectangle rectangle = new PDRectangle(width, height);
                PDPage page = new PDPage(rectangle);
                document.addPage(page);

                PDImageXObject pdImage = PDImageXObject.createFromFileByContent(imageFile, document);
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.drawImage(pdImage, 0, 0, width, height);
                }
            }
            document.save(pdfFile);
        }
    }

    // 간단한 텍스트 PDF 생성
    public String generatePDF(String content) {
        String filePath = "output.pdf";
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText(content);
                contentStream.newLine();
                contentStream.endText();
            }

            document.save(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error generating PDF", e);
        }
        return filePath;
    }
}



