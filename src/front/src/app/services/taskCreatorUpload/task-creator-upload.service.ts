import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TaskCreatorUploadService {

  private baseUrl: string = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  createTask(name: string,
             description: string,
             inputBLock: string,
             outputBlock: string,
             riseTime: string,
             settlingTime: string,
             settlingMax: string,
             settlingMin: string,
             overshoot: string,
             undershoot: string,
             peak: string,
             peakTime: string,
             file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('name', name);
    formData.append('description', description);
    formData.append('inputBlock', inputBLock);
    formData.append('outputBlock', outputBlock);
    formData.append("riseTime", riseTime);
    formData.append('settlingTime', settlingTime);
    formData.append('settlingMin', settlingMin);
    formData.append('settlingMax', settlingMax);
    formData.append('overshoot', overshoot);
    formData.append('undershoot', undershoot);
    formData.append('peak', peak);
    formData.append('peakTime', peakTime);
    formData.append('file', file);
    console.log(formData.get('file'));
    const req = new HttpRequest('POST', `${this.baseUrl}/uploadTask`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.httpClient.request(req);
  }
}
