import { Component, OnInit } from '@angular/core';
import {TestsService} from '../shared/tests.service';
import {PositionService} from '../shared/position.service';
import {MatDialog, MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-candidate-panel',
  templateUrl: './candidate-panel.component.html',
  styleUrls: ['./candidate-panel.component.css']
})
export class CandidatePanelComponent implements OnInit {
  testList: any;
  positionList;
  userLanguage;
  constructor(private testsService: TestsService, private postionService: PositionService, private _snackBar: MatSnackBar, public dialog: MatDialog) { }

  ngOnInit() {
    if(localStorage.getItem('language'))
      this.userLanguage = localStorage.getItem('language');
    this.postionService.getAllPositions().subscribe(((positions: any) => {
      this.positionList = positions.list.filter(position => position.active === true).map(pos => pos.name);
      this.testsService.getAllTests().subscribe(((tests: any) => {
        this.testList = tests.list.filter(item =>  this.positionList.includes(item.position));
      }));
    }));

  }

  Submit(data) {
    console.log(data);
  }
}
