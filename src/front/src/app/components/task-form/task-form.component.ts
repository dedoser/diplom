import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {TaskCreatorUploadService} from "../../services/taskCreatorUpload/task-creator-upload.service";
import {HttpResponse} from "@angular/common/http";


@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css']
})
export class TaskFormComponent implements OnInit {

  taskFormGroup: FormGroup;
  fileName: string = null;
  message: string;
  selectedFiles?: FileList;
  currentFile: File;

  constructor(private formBuilder: FormBuilder,
              private taskUploadService: TaskCreatorUploadService) { }

  ngOnInit(): void {
    this.taskFormGroup = this.formBuilder.group({
      task: this.formBuilder.group({
        name: new FormControl('', [Validators.required]),
        description: new FormControl('', [Validators.required]),
        inputBlock: new FormControl('', [Validators.required]),
        outputBlock: new FormControl('', [Validators.required]),
        file: ['']
      }),
      params: this.formBuilder.group({
        riseTime: '',
        settlingTime: '',
        settlingMax: '',
        settlingMin: '',
        overshoot: '',
        undershoot: '',
        peak: '',
        peakTime: ''
      })
    });
  }

  get name() {
    return this.taskFormGroup.get('task.name');
  }

  get description() {
    return this.taskFormGroup.get('task.description');
  }

  get file() {
    return this.taskFormGroup.get('task.file');
  }

  get inputBlock() {
    return this.taskFormGroup.get('task.inputBlock');
  }

  get outputBlock() {
    return this.taskFormGroup.get('task.outputBlock');
  }

  get riseTime() {
    return this.taskFormGroup.get('params.riseTime');
  }

  get settlingTime() {
    return this.taskFormGroup.get('params.settlingTime');
  }

  get settlingMin() {
    return this.taskFormGroup.get('params.settlingMin');
  }

  get settlingMax() {
    return this.taskFormGroup.get('params.settlingMax');
  }

  get overshoot() {
    return this.taskFormGroup.get('params.overshoot');
  }

  get undershoot() {
    return this.taskFormGroup.get('params.undershoot');
  }

  get peak() {
    return this.taskFormGroup.get('params.peak');
  }

  get peakTime() {
    return this.taskFormGroup.get('params.peakTime');
  }

  onFileChanged() {
    const file: File | null = this.selectedFiles.item(0);
    this.fileName = file.name;
    this.currentFile = file;
    this.taskFormGroup.patchValue({
      file: file
    });
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  onSubmit() {
    console.log("start saving task");
    this.onFileChanged();
    console.log(this.currentFile);
    this.taskUploadService.createTask(
      this.name.value,
      this.description.value,
      this.inputBlock.value,
      this.outputBlock.value,
      this.riseTime.value,
      this.settlingTime.value,
      this.settlingMax.value,
      this.settlingMin.value,
      this.overshoot.value,
      this.undershoot.value,
      this.peak.value,
      this.peakTime.value,
      this.currentFile
    ).subscribe({
      next: (event: any) => {
        if (event instanceof HttpResponse) {
          this.message = event.body.message;
        }
      },
      error: (err: any) => {
        console.log(err);
        if (err.error && err.error.message) {
          this.message = err.error.message;
        } else {
          this.message = err.body.message;
        }
      }
    });
  }
}
