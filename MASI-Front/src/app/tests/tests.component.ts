import { Component, OnInit } from '@angular/core';
import {MatDialog, MatSnackBar} from '@angular/material';
import {TestsService} from '../shared/tests.service';
import {EditUserDialogComponent} from '../edit-user-dialog/edit-user-dialog.component';
import {EditQuestionComponent} from '../edit-question/edit-question.component';
import {PositionService} from '../shared/position.service';
import {AddQuestionComponent} from '../add-question/add-question.component';
import {AddTestDialogComponent} from '../add-test-dialog/add-test-dialog.component';

@Component({
  selector: 'masi-tests',
  templateUrl: './tests.component.html',
  styleUrls: ['./tests.component.css']
})
export class TESTSComponent implements OnInit {
  userRole = '';
  testList: any;
  positionList;
  tmpType = 'OPEN';
  tmpName = 'Test name'
  constructor(private testsService: TestsService, private postionService: PositionService, private _snackBar: MatSnackBar, public dialog: MatDialog) { }
  ngOnInit() {
    if (localStorage.getItem('role') !== '') {
      this.userRole = localStorage.getItem('role');
    }
    this.testsService.getAllTests().subscribe(((tests: any) => {
      this.testList = tests.list;
      console.log(this.testList);
      if (this.userRole === 'RED') {
        this.testList = this.testList.filter((item) => item.redactor === localStorage.getItem('name'));
      }
    }));
    this.postionService.getAllPositions().subscribe(((positions: any) => {
      this.positionList = positions.list;
      console.log(this.positionList);
    }));
  }
  editQuestion(question, index, testID, versionID) {
    let data = {test: testID, version: versionID, id: index, type: question.type, description: question.description, scale: [0, 0], options: ['', '', '', '' ]};
    if(question.scale)
      data.scale = question.scale
    if(question.options)
      data.options = question.options;
    const dialogRef = this.dialog.open(EditQuestionComponent, {
      width: '900px',
      data
    });
    dialogRef.afterClosed().subscribe(result => {
      this.testList[result.test].versions[result.version].questions[result.id] = {type: result.type, description: result.description,
        options: result.options, scale: result.scale};
    });
  }
  saveChanges(test) {
    console.log(test);
  }
  deleteQuestion(test, questionID) {
    test.questions = test.questions.filter((item, i) => i !== questionID);
    console.log(test.questions);
  }

  addQuestion(questions: any) {
    let data = {type: this.tmpType, description: '', scale: [0, 0], options: ['', '', '', '' ]};
    const dialogRef = this.dialog.open(AddQuestionComponent, {
      width: '900px',
      data
    });
    dialogRef.afterClosed().subscribe(result => {
      questions.push({
        type: result.data.type, description: result.description, scale: result.data.scale, options: result.data.options
      });
    });
  }

  removeTest(test: any) {
    this.testList = this.testList.filter(t => t !== test);
  }

  newTest() {
    let data = {name: this.tmpName};
    const dialogRef = this.dialog.open(AddTestDialogComponent, {
      width: '500px',
      data
    });
    dialogRef.afterClosed().subscribe(result => {
      this.testList.push({
        name: result.name, redactor: localStorage.getItem('name'),
        position: '', versions: [{language: 'ENG', questions: []}]
      });
    });
  }
}
