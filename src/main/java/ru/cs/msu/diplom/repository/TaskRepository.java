package ru.cs.msu.diplom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import ru.cs.msu.diplom.entity.Task;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "task", path = "task")
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByUserId(@RequestParam("id") Long id, Pageable pageable);
}
