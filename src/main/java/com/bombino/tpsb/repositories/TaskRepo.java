package com.bombino.tpsb.repositories;

import com.bombino.tpsb.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    List<Task> findByCompleted(boolean completed);

}