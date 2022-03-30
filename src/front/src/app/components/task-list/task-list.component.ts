import { Component, OnInit } from '@angular/core';
import {TaskService} from "../../services/task/task.service";
import {Task} from "../../common/task";
import {ActivatedRoute, Route} from "@angular/router";

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks: Task[] = [];

  constructor(private taskService: TaskService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      () => {
        console.log("Get tasks");
        this.listTasks()
      }
    )
  }

  listTasks() {
    this.taskService.getTaskList().subscribe(
      data => {
        this.tasks = data;
      }
    )
  }

  createTask() {

  }
}
