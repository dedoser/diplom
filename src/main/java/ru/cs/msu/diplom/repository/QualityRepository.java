package ru.cs.msu.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.cs.msu.diplom.entity.Quality;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "quality", path = "quality")
public interface QualityRepository extends JpaRepository<Quality, Long> {
}
