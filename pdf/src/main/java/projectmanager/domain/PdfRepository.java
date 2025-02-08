package projectmanager.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projectmanager.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "pdfs", path = "pdfs")
public interface PdfRepository extends PagingAndSortingRepository<Pdf, Long> {}
