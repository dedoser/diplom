import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  private baseUrl = "http://localhost:8080"
  private taskId: number;
  private userId: number;

  constructor(private httpClient: HttpClient) {}

  upload(file: File, taskId: number): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('taskId', taskId.toString());
    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.httpClient.request(req);
  }

  getFiles(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/files`);
  }

  setIds(userId: number, taskId: number) {
    this.userId = userId;
    this.taskId = taskId;
  }
}
