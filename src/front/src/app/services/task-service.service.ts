import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Task} from 'src/app/common/task'

@Injectable({
  providedIn: 'root'
})
export class TaskServiceService {

  private baseUrl = 'http://localhost:8080/tasks'

  constructor(private httpClient: HttpClient) { }

  getTaskList(): Observable<Task[]> {
    return this.httpClient.get<getResponseTask>(this.baseUrl).pipe(
      map(response => response._embedded.tasks)
    );
  }


}

interface getResponseTask {
  _embedded: {
    tasks: Task[];
  }
}
