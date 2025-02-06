package projectmanager.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import projectmanager.PdfApplication;
import projectmanager.domain.PdfGenerated;

@Entity
@Table(name = "Pdf_table")
@Data
//<<< DDD / Aggregate Root
public class Pdf {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String projectId;

    private String taskId;

    private String status;

    private String createdAt;

    private String completedAt;

    private String filePath;

    public static PdfRepository repository() {
        PdfRepository pdfRepository = PdfApplication.applicationContext.getBean(
            PdfRepository.class
        );
        return pdfRepository;
    }

    //<<< Clean Arch / Port Method
    public static void generate(TaskCompleted taskCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Pdf pdf = new Pdf();
        repository().save(pdf);

        PdfGenerated pdfGenerated = new PdfGenerated(pdf);
        pdfGenerated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(taskCompleted.get???()).ifPresent(pdf->{
            
            pdf // do something
            repository().save(pdf);

            PdfGenerated pdfGenerated = new PdfGenerated(pdf);
            pdfGenerated.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
