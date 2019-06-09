import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import {User} from '../models/User.model';

@Injectable()
export class UserService {
  readonly rootUrl = 'http://localhost:35257';
  constructor(private http: HttpClient) { }

  registerUser(user: User) {
    const body: User = {
      name: user.name,
      password: user.password,
      email: user.email,
    };
    const reqHeader = new HttpHeaders({'No-Auth': 'True'});
    return this.http.post(this.rootUrl + '/api/User/Register', body, { headers : reqHeader});
  }

  userAuthentication(userName, password) {
    const data = 'username=' + userName + '&password=' + password + '&grant_type=password';
    const reqHeader = new HttpHeaders({ 'Content-Type': 'application/x-www-urlencoded', 'No-Auth': 'True' });
    return this.http.post(this.rootUrl + '/api/User/Login', data, { headers: reqHeader });
  }

  getUserClaims() {
   return  this.http.get(this.rootUrl + '/api/User/GetUserClaims');
  }
  changeLanguage(data) {
   return  this.http.put(this.rootUrl + '/api/User/Language', data);
  }
  editUser(user) {
    return this.http.put(this.rootUrl + '/api/User/mod', user);
  }
}
