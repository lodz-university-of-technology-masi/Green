import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import {User} from '../models/User.model';

@Injectable()
export class PositionService {
  readonly rootUrl = 'http://localhost:35257';
  constructor(private http: HttpClient) { }

  getAllPositions() {
    return  this.http.get(this.rootUrl + '/api/position/getAll');
  }
  addPosition(data) {
    return  this.http.post(this.rootUrl + '/api/position/add', data);
  }
  editPosition(data) {
    return  this.http.put(this.rootUrl + '/api/position/mod', data);
  }
}
