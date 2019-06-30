import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute, ParamMap, RoutesRecognized} from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import {UserService} from '../shared/user.service';
import {debuglog} from 'util';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  userClaims: any;
  showDetails = false;
  currentLang;
  languages = [{key: 'ENG', show: 'English'}, {key: 'POL', show: 'Polish'}];

  constructor(private router: Router, private userService: UserService, private route: ActivatedRoute) {
    router.events.subscribe((val) => {
      if (val instanceof RoutesRecognized && val.urlAfterRedirects === '/main') {
        if (localStorage.getItem('userToken') !== '') {
          this.userService.getUserClaims(localStorage.getItem('name')).subscribe((data: any) => {
            this.userClaims = data.response;
            this.currentLang = data.response.language;
            localStorage.setItem('language', this.userClaims.language);
            localStorage.setItem('role', this.userClaims.role);
            localStorage.setItem('name', this.userClaims.name);
            this.router.navigateByUrl('main/home');
          });
        }
      }
    });
  }

  ngOnInit() {
    if (localStorage.getItem('userToken') !== '') {
      this.userService.getUserClaims(localStorage.getItem('name')).subscribe((data: any) => {
        this.userClaims = data;
        localStorage.setItem('language', this.userClaims.language);
        localStorage.setItem('role', this.userClaims.role);
        localStorage.setItem('name', this.userClaims.name);
        this.currentLang = data.language;
        this.router.navigateByUrl('main/home');
      });
    }
  }

  ShowHideProfileDetails() {
    this.showDetails = !this.showDetails;
  }

  Logout() {
    localStorage.removeItem('userToken');
    localStorage.removeItem('role');
    this.showDetails = false;
    this.userClaims = '';
    this.router.navigateByUrl('main/login');
  }

  Language(lang) {
    const newLang = lang.value;
    this.userService.changeLanguage({language: newLang, name: localStorage.getItem('name')}).subscribe((data: any) => {
      localStorage.setItem('language', newLang);
    });
  }
}
