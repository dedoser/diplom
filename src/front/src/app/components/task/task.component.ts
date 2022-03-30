import { Component, OnInit } from '@angular/core';
import {Task} from "../../common/task";
import {TaskService} from "../../services/task/task.service";
import {ActivatedRoute} from "@angular/router";
import {FileUploadService} from "../../services/file-upload/file-upload.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  task: Task;

  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';

  constructor(private taskService: TaskService,
              private route: ActivatedRoute,
              private uploadService: FileUploadService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      () => {
        this.handleTaskDetails();
        // this.uploadService.setIds(this.task.id)
      }
    )
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  handleTaskDetails() {
    const taskId: number = +this.route.snapshot.paramMap.get("id")!;

    this.taskService.getTask(taskId).subscribe(
      data => this.task = data
    );
  }

  upload(): void {
    this.progress = 0;
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      if (file) {
        this.currentFile = file;
        this.uploadService.upload(this.currentFile, this.task.id).subscribe({
          next: (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round(100 * event.loaded / event.total);
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;
            }
          },
          error: (err: any) => {
            console.log(err);
            this.progress = 0;
            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }
            this.currentFile = undefined;
          }
        });
      }
      this.selectedFiles = undefined;
    }
  }

  uploadSolution() {

  }
}
