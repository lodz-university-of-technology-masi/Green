import {Component, ElementRef, OnInit, ViewChild, HostListener} from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../shared/user.service';
<<<<<<< Updated upstream
import {ModService} from '../shared/mod.service';
=======
import { HotkeysService, Hotkey } from 'angular2-hotkeys';
import { MeasurementProvider } from '../shared/MeasurementProvider';

>>>>>>> Stashed changes

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
<<<<<<< Updated upstream
  role: any;


  public constructor(private router: Router, private userService: UserService, private modService: ModService) {
=======
  userClaims: any;
  showDetails: boolean =  false;
  showPoiList: boolean =  false;
  poiList: any;
  
  public constructor(private router: Router, private userService: UserService, private _hotkeysService: HotkeysService, private _measurementProvider: MeasurementProvider) {
    this._hotkeysService.add(new Hotkey('shift+d', (event: KeyboardEvent): boolean => {
      alert("po");
      this._measurementProvider.Measure;
      return false;
    }));
    this._hotkeysService.add(new Hotkey('shift+w', (event: KeyboardEvent): boolean => {
      alert("Measuring canceled");
      this._measurementProvider.MeasureCancel;
      return false;
    }));
>>>>>>> Stashed changes
  }
  ngOnInit() {
    if (localStorage.getItem('role') !== '') {
      this.role = localStorage.getItem(('role'));

    }
  }
<<<<<<< Updated upstream
=======
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
        this._measurementProvider.Clicked;
    }
>>>>>>> Stashed changes
}
