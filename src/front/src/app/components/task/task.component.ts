import { Component, OnInit } from '@angular/core';
import {Task} from "../../common/task";
import {TaskService} from "../../services/task.service";
import {ActivatedRoute} from "@angular/router";
import {FileUploadService} from "../../services/file-upload.service";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  task: Task;

  constructor(private taskService: TaskService,
              private route: ActivatedRoute,
              private fileUploadService: FileUploadService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      () => this.handleTaskDetails()
    )
  }

  handleTaskDetails() {
    const taskId: number = +this.route.snapshot.paramMap.get("id")!;

    this.taskService.getTask(taskId).subscribe(
      data => this.task = data
    );
  }

  onFileUpload(event) {
    this.fileUploadService.onFileSelected(event);
  }
}
