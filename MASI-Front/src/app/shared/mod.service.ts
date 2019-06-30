import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import {User} from '../models/User.model';

@Injectable()
export class ModService {
  readonly rootUrl = '/api';
  constructor(private http: HttpClient) { }

  getAllUsers() {
    return  this.http.get(this.rootUrl + '/member/getAll');
  }

}
