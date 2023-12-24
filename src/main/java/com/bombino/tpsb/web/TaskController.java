package com.bombino.tpsb.web;

import com.bombino.tpsb.entities.Task;
import com.bombino.tpsb.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class TaskController {
    @Autowired
    TaskRepo taskRepo;

    @GetMapping("/tasks")
    List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        tasks.addAll(taskRepo.findAll());
        return tasks;

    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> getTask(Integer id) {
        Optional<Task> taskData = taskRepo.findById(id);
        if (taskData.isPresent()) {
            return new ResponseEntity<>(taskData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        }
    }
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTutorial(@RequestBody Task task) {
        try {
            Task _task = taskRepo
                    .save(new Task(task.getTitle(), task.getDescription(), false));
            return new ResponseEntity<>(_task, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTutorial(@PathVariable("id") Integer id, @RequestBody Task task) {
        Optional<Task> tutorialData = taskRepo.findById(id);

        if (tutorialData.isPresent()) {
            Task _tutorial = tutorialData.get();
            _tutorial.setTitle(task.getTitle());
            _tutorial.setDescription(task.getDescription());
            _tutorial.setCompleted(task.isCompleted());
            return new ResponseEntity<>(taskRepo.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Integer id) {
        try {
            taskRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            taskRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/tasks/completed")
    public ResponseEntity<List<Task>> findByPublished() {
        try {
            List<Task> tasks = taskRepo.findByCompleted(true);

            if (tasks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
