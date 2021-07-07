package com.app.service;


import com.app.domain.Task;
import com.app.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor //creates constructor for all final fields
public class DbService {

    private final TaskRepository repository;

    public DbService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Optional<Task> getTask(final Long id) {
        return  repository.findTaskById(id);
    }

    public Task saveTask(Task task){
        return repository.save(task);
    };

    public void deleteTask(final Long id){
        this.repository.deleteById(id);
    }

}
