import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import {User} from '../models/User.model';

@Injectable()
export class TestsService {
  readonly rootUrl = '/api';
  constructor(private http: HttpClient) { }

  getAllTests() {
    return  this.http.get(this.rootUrl + '/test/getAll');
  }

  addTest(data) {
    const reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'No-Auth': 'True'});
    return this.http.post(this.rootUrl + '/test/add', data, { headers : reqHeader});
  }

  saveChanges(data) {
    const reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'No-Auth': 'True'});
    return this.http.put(this.rootUrl + '/test/edit', data, { headers : reqHeader});
  }
}
