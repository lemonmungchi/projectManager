package projectmanager.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projectmanager.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "tasks", path = "tasks")
public interface TaskRepository
    extends PagingAndSortingRepository<Task, Long> {}
