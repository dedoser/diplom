package ru.cs.msu.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.cs.msu.diplom.entity.Task;

@RepositoryRestResource(collectionResourceRel = "task", path = "task")
public interface TaskRepository extends JpaRepository<Task, Long> {
}
