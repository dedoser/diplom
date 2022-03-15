import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../common/user";
import {Observable, map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/users';

  constructor(private httpClient: HttpClient) { }

  getUserList(): Observable<User[]> {
    return this.httpClient.get<getUsersResponse>(this.baseUrl).pipe(
      map(response => response._embedded.users)
    );
  }

  getUser(id: number): Observable<User> {
    const searchUrl = `${this.baseUrl}/${id}`;
    return this.httpClient.get<User>(searchUrl);
  }
}

interface getUsersResponse {
  _embedded: {
    users: User[]
  }
}
