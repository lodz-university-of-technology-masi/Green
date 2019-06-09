import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../shared/user.service';
import {ModService} from "../shared/mod.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  role: any;


  public constructor(private router: Router, private userService: UserService, private modService: ModService) {
  }
  ngOnInit() {
    if (localStorage.getItem('role') !== '') {
      this.role = localStorage.getItem(('role'));
    }
    }

}
