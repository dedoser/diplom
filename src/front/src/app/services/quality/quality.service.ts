import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Quality} from "../../common/quality";
import {Solution} from "../../common/solution";

@Injectable({
  providedIn: 'root'
})
export class QualityService {

  private baseUrl = 'http://localhost:8080/solution';

  constructor(private httpClient: HttpClient) { }

  getQuality(id: number) : Observable<Quality> {
    return this.httpClient.get<Quality>(
      `${this.baseUrl}/${id}/quality`
    );
  }
}
