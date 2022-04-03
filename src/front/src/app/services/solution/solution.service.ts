import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Solution} from "../../common/solution";
import {Observable, map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SolutionService {

  private baseUrl = 'http://localhost:8080/solution';

  constructor(private httpClient: HttpClient) {
  }

  getSolutionList(taskId: number): Observable<Solution[]> {
    return this.httpClient
      .get<getSolutionList>(`${this.baseUrl}/search/findByTaskId?taskId=${taskId}`)
      .pipe(
        map(response => {
          return response._embedded.solution;
        })
      );
  }

  getSolution(id: number): Observable<Solution> {
    return this.httpClient.get<Solution>(
      `${this.baseUrl}/${id}`
    );
  }
}

interface getSolutionList {
  _embedded: {
    solution: Solution[];
  }
}
