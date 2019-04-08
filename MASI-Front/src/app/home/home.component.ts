import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../shared/user.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  userClaims: any;
  showDetails: boolean =  false;
  showPoiList: boolean =  false;
  poiList: any;
  public constructor(private router: Router, private userService: UserService) {
  }
  ngOnInit() {
    this.userService.getUserClaims().subscribe((data: any) => {
      this.userClaims = data;
    });
  }

  ShowHideProfileDetails() {
    this.showDetails = !this.showDetails;
  }
  ShowHidePoiList() {
    this.showPoiList = !this.showPoiList;
    console.log(this.poiList);
  }
  Logout() {
    localStorage.removeItem('userToken');
    this.router.navigate(['/login']);
  }


}
