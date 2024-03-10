package org.example.ex1.Controller;

import org.example.ex1.Api.ApiResponse;
import org.example.ex1.Model.TaskTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/task")
public class TaskTrackerController {
    ArrayList<TaskTracker> tasks = new ArrayList<>();

    @GetMapping("/display")
    public ArrayList<TaskTracker> displayTasks() {
        return tasks;
    }

    @PostMapping("/create")
    public ApiResponse createTask(@RequestBody TaskTracker task) {
        tasks.add(task);
        return new ApiResponse("Task created");
    }
    @PutMapping("/update/{index}")
    public ApiResponse updatedTask(@PathVariable int index, @RequestBody TaskTracker task){
        tasks.set(index,task);
        return new ApiResponse("Task updated");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        tasks.remove(index);
        return new ApiResponse("Task deleted");
    }

    @PutMapping("/status/{index}")
    public ApiResponse changeStatus(@PathVariable int index){
  if(tasks.get(index).getStatus().equals("not done")){
      tasks.get(index).setStatus("done");
      return new ApiResponse("Status changed");
  }else{
      return new ApiResponse("Status is done");

  }
    }


    @GetMapping("/search/{title}")
    public ArrayList<TaskTracker> searchTask(@PathVariable String title){
     ArrayList<TaskTracker>search= new ArrayList<>();

     for(TaskTracker task: tasks){
         if(task.getTitle().contains(title)){
             search.add(task);
         }
     }
     return search;
    }
    }


