import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../user';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:8080/api';
  private user = new User();

  // private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private httpClient: HttpClient) { }

  // GetAll
  getUsers(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/users');
  }

  // GetById
  getUser(id: number) {
    return this.httpClient.get(this.baseUrl + '/user/' + id);
  }

  // Delete
  deleteUser(id: number) {
    return this.httpClient.delete(this.baseUrl + '/user/' + id);
  }

  // Create User
  createUser(user: User): Observable<any> {
    // JSON.stringify
    return this.httpClient.post(this. baseUrl + '/user', user);
  }

  // Update User
  updateUser(user: User): Observable<any> {
    return this.httpClient.put(this.baseUrl + '/user', user);
  }

  // Catch Error
  private errorHandler(error: HttpErrorResponse): Observable<any> {
     throw(error || 'SERVER ERROR');
  }

  setter(user: User) {
    this.user = user;
  }
  getter() {
    return this.user;
  }




}

