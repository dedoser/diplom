import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TaskCreatorUploadService {

  private baseUrl: string = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  createTask(name: string, description: string, file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('name', name);
    formData.append('description', description);
    formData.append('file', file);
    console.log(formData.get('file'));
    const req = new HttpRequest('POST', `${this.baseUrl}/uploadTask`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.httpClient.request(req);
  }
}
