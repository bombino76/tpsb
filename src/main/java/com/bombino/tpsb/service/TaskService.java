package com.bombino.tpsb.service;

import com.bombino.tpsb.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepo repo;


}
