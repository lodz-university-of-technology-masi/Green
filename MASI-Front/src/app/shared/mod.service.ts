import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import {User} from '../models/User.model';

@Injectable()
export class ModService {
  readonly rootUrl = 'http://localhost:35257';
  constructor(private http: HttpClient) { }

  getAllUsers(){
    return  this.http.get(this.rootUrl + '/api/User/getAll');
  }


}
