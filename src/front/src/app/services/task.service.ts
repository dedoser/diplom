import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Task} from 'src/app/common/task'

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private baseUrl = 'http://localhost:8080/task'

  constructor(private httpClient: HttpClient) { }

  getTaskList(): Observable<Task[]> {
    return this.httpClient.get<getResponseTask>(this.baseUrl).pipe(
      map(response => {
        console.log(response._embedded.task)
        return response._embedded.task;
      })
    );
  }

  getTask(id: number): Observable<Task> {
    const searchUrl = `${this.baseUrl}/${id}`;
    return this.httpClient.get<Task>(searchUrl);
  }
}

interface getResponseTask {
  _embedded: {
    task: Task[];
  }
}
