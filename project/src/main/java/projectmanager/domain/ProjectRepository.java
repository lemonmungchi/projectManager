package projectmanager.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projectmanager.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "projects", path = "projects")
public interface ProjectRepository
    extends PagingAndSortingRepository<Project, Long> {}
