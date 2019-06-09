import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import {User} from '../models/User.model';

@Injectable()
export class TestsService {
  readonly rootUrl = 'http://localhost:35257';
  constructor(private http: HttpClient) { }

  getAllTests() {
    return  this.http.get(this.rootUrl + '/api/tests/getAll');
  }

  saveChanges(test){
    return this.http.put(this.rootUrl + '/api/tests/mod', test);
  }
}
