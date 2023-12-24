package com.bombino.tpsb.service;

import com.bombino.tpsb.entities.Task;
import com.bombino.tpsb.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements ITaskService {




    @Override
    public List<Task> findAll() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        tasks.add(new Task( "Mobile", "CLK98123", true));
        tasks.add(new Task( "Smart TV", "LGST09167", false));
        tasks.add(new Task("Washing Machine", "38753BK9", false));

        return tasks;
    }
}
