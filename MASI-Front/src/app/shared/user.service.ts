import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import {User} from '../models/User.model';

@Injectable()
export class UserService {
  readonly rootUrl = '/api';
  constructor(private http: HttpClient) { }

  registerUser(user: User) {
    const body: User = {
      name: user.name,
      password: user.password,
      language: user.language,
      email: user.email,
      role: 'CAN'
    };
    const reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'No-Auth': 'True'});
    return this.http.post(this.rootUrl + '/member/register', body, { headers : reqHeader});
  }

  userAuthentication(userName, password) {
    const body: any = {
      name: userName,
      password: password,
    };
    const reqHeader = new HttpHeaders({ 'Content-Type': 'application/json', 'No-Auth': 'True' });
    return this.http.post(this.rootUrl + '/member/login', body, { headers: reqHeader });
  }
  getUserClaims(userName) {
    const body: any = {
      name: userName,
      password: '',
    };
    const reqHeader = new HttpHeaders({ 'Content-Type': 'application/json', 'No-Auth': 'True' });
    return  this.http.post(this.rootUrl + '/member/GetUserClaims', body, { headers: reqHeader });
  }
  changeLanguage(data) {
   return  this.http.put(this.rootUrl + '/member/Language', data);
  }
  editUser(user) {
    return this.http.put(this.rootUrl + '/member/edit', user);
  }
}
