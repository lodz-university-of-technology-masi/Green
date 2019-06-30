import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import {User} from '../models/User.model';

@Injectable()
export class PositionService {
  readonly rootUrl = '/api';
  constructor(private http: HttpClient) { }

  getAllPositions() {
    return  this.http.get(this.rootUrl + '/position/getAll');
  }
  addPosition(data) {
    const reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'No-Auth': 'True'});
    return this.http.post(this.rootUrl + '/position/add', data, { headers : reqHeader});
  }
  editPosition(data) {
    return  this.http.put(this.rootUrl + '/position/mod', data);
  }
}
