import {Component, ElementRef, OnInit, ViewChild, HostListener} from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../shared/user.service';
import { HotkeysService, Hotkey } from 'angular2-hotkeys';
import { MeasurementProvider } from '../shared/MeasurementProvider';

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
  role: any;
  
  public constructor(private router: Router, private userService: UserService, private _hotkeysService: HotkeysService, private _measurementProvider: MeasurementProvider) {
    this._hotkeysService.add(new Hotkey('shift+d', (event: KeyboardEvent): boolean => {
      var ret = this._measurementProvider.Measure();
      if(ret)
        alert(ret);
      return false;
    }));
    this._hotkeysService.add(new Hotkey('shift+w', (event: KeyboardEvent): boolean => {
      alert("Measuring canceled");
      this._measurementProvider.MeasureCancel;
      return false;
    }));
  }
  ngOnInit() {
    if (localStorage.getItem('role') !== '') {
      this.role = localStorage.getItem(('role'));

    }
  }
  ShowHidePoiList() {
    this.showPoiList = !this.showPoiList;
    console.log(this.poiList);
  }
  Logout() {
    localStorage.removeItem('userToken');
    this.router.navigate(['/login']);
  }

  @HostListener('document:click', ['$event'])
    documentClick(event: MouseEvent) {
        this._measurementProvider.Clicked();
    }
}
