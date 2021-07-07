package com.app.repository;

import com.app.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAll();

    Optional<Task> findTaskById(Long id);
}
