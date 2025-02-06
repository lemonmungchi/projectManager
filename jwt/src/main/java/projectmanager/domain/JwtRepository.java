package projectmanager.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projectmanager.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "jwts", path = "jwts")
public interface JwtRepository extends PagingAndSortingRepository<Jwt, Long> {}
