package com.bombino.tpsb.service;

import com.bombino.tpsb.entities.Task;

import java.util.List;

public interface ITaskService {
    List<Task> findAll();
}
