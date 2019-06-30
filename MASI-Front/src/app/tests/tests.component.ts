import { Component, OnInit } from '@angular/core';
import {MatDialog, MatSnackBar} from '@angular/material';
import {TestsService} from '../shared/tests.service';
import {EditUserDialogComponent} from '../edit-user-dialog/edit-user-dialog.component';
import {EditQuestionComponent} from '../edit-question/edit-question.component';
import {PositionService} from '../shared/position.service';
import {AddQuestionComponent} from '../add-question/add-question.component';
import {AddTestDialogComponent} from '../add-test-dialog/add-test-dialog.component';
import {TranslatePipe} from '../shared/TranslatePipe';
import translate, { setCORS } from "google-translate-api-browser";
setCORS("http://cors-anywhere.herokuapp.com/");

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
  tmpName = 'Test name';
  searchTextWiki = '';
  searchTextSynonym = '';
  languages = [{key: 'ENG', show: 'English'}, {key: 'POL', show: 'Polish'}];
  currentLang = 'ENG';
  csvContent: string;

  constructor(private testsService: TestsService,
              private postionService: PositionService, private _snackBar: MatSnackBar, public dialog: MatDialog) { }
  ngOnInit() {
    if (localStorage.getItem('role') !== '') {
      this.userRole = localStorage.getItem('role');
    }
    this.testsService.getAllTests().subscribe(((tests: any) => {
      this.testList = tests.response;
      console.log(this.testList);
      if (this.userRole === 'RED') {
        this.testList = this.testList.filter((item) => item.redactor === localStorage.getItem('name'));
      }
    }));
    this.postionService.getAllPositions().subscribe(((positions: any) => {
      this.positionList = positions.response;
      console.log(this.positionList);
    }));
  }
  editQuestion(question, index, testID, versionID) {
    let data = {test: testID, version: versionID, id: index, type: question.type,
      description: question.description, minVal: 0, maxVal: 0, option1: '', option2: '', option3: ''};
    if(question.minVal && question.maxVal)
      data.minVal = question.minVal
      data.maxVal = question.maxVal
    if(question.option1)
      data.option1 = question.option1;
      data.option2 = question.option2;
      data.option3 = question.option3;
    const dialogRef = this.dialog.open(EditQuestionComponent, {
      width: '900px',
      data
    });
    dialogRef.afterClosed().subscribe(result => {
      this.testList[result.test].versions[result.version].questions[result.id] = {type: result.type, description: result.description,
        option1: result.option1, option2: result.option2, option3: result.option3, minVal: result.minVal, maxVal: result.maxVal };
    });
  }
  saveChanges(test) {
    console.log(test);
    this.testsService.saveChanges(test).subscribe(((ew: any) => {
      this.testsService.getAllTests().subscribe(((tests: any) => {
        this.testList = tests.response;
        console.log(this.testList);
        if (this.userRole === 'RED') {
          this.testList = this.testList.filter((item) => item.redactor === localStorage.getItem('name'));
        }
      }));
        }));
  }
  deleteQuestion(test, questionID) {
    test.questions = test.questions.filter((item, i) => i !== questionID);
    console.log(test.questions);
  }

  addQuestion(questions: any) {
    let data = {type: this.tmpType, description: '', minVal: 0, maxVal: 0, option1: '', option2: '', option3: ''};
    const dialogRef = this.dialog.open(AddQuestionComponent, {
      width: '900px',
      data
    });
    dialogRef.afterClosed().subscribe(result => {
      questions.push({
        type: result.type, description: result.description,
        option1: result.option1, option2: result.option2, option3: result.option3, minVal: result.minVal, maxVal: result.maxVal
      });
    });
  }

  removeTest(test: any) {
    this.testList = this.testList.filter(t => t !== test);
  }

  searchWiki() {
    const win = window.open('https://pl.wikipedia.org/wiki/Special:Search?search=' + this.searchTextWiki, '_blank');
    win.focus();
  }
  searchSynonym() {
    const win = window.open('https://www.thesaurus.com/browse/' + this.searchTextSynonym, '_blank');
    win.focus();
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
      this.testsService.addTest({name: result.name, redactor: localStorage.getItem('name'),
        position: '', versions: [{language: 'ENG', questions: []}]}).subscribe(((ew: any) => {
        this.testsService.getAllTests().subscribe(((tests: any) => {
          this.testList = tests.response;
          console.log(this.testList);
          if (this.userRole === 'RED') {
            this.testList = this.testList.filter((item) => item.redactor === localStorage.getItem('name'));
          }
        }));
      }));
    });
  }

  addVersion(test: any) {
    const newVersion = {...test.versions.filter(v => v.language === 'ENG' )[0]};
    newVersion.language = this.currentLang;
    test.versions.push(newVersion);
  }
  addVersionTrans(test: any) {
    const newVersion: any =  new Object;
    let translationShort = 'en';
    if (this.currentLang === 'POL')
      translationShort = 'pl';
    const questions = JSON.parse(JSON.stringify(test.versions.filter(v => v.language === 'ENG' )[0].questions))
      newVersion.language = this.currentLang;
      newVersion.questions = questions;
      newVersion.questions.map(question => { translate(question.description, { to: translationShort }).then((res: any) =>
      {return question.description = res.text})});
      // newVersion.questions.filter(question => question.type === 'CHOOSE' )
      //   .map(question => question.options.map(option => { translate(option,
      //     { to: translationShort }).then((res: any) =>
      // { return option = res.text})} ))
    test.versions.push(newVersion);
    this.saveChanges(test);
  }

  onFileLoad(fileLoadedEvent, context) {
    const textFromFileLoaded = fileLoadedEvent.target.result;
    const questionList = textFromFileLoaded.split('\n');
    let questions = [];
    questionList.forEach(question => {
      question = question.split((';'));
      if (question.length > 3) {
        if (question[1] === 'O') {
          questions.push({type: 'OPEN', description: question[3]});
        }
        if (question[1] === 'W') {
          questions.push({type: 'CHOOSE', description: question[3],
          option1: question[5], option2: question[6], option3: question[7]});
        }
        if (question[1] === 'L') {
          questions.push({type: 'NUMBER', description: question[3]});
        }
        if (question[1] === 'S') {
          questions.push({type: 'SCALE', description: question[3], minVal: question[5],
          maxVal: question[4]});
        }
      }
    });
    this.testsService.addTest({name: 'Krysia', redactor: localStorage.getItem('name'),
      position: '', versions: [{language: 'ENG', questions: questions}]}).subscribe(((ew: any) => {
      context.testsService.getAllTests().subscribe(((tests: any) => {
        context.testList = tests.response;
        console.log(context.testList);
        if (context.userRole === 'RED') {
          context.testList = context.testList.filter((item) => item.redactor === localStorage.getItem('name'));
        }
      }));
    }));
  }

  onFileSelect(input: HTMLInputElement) {

    let files = input.files;
    let content = this.csvContent;
    if (files && files.length) {

      let fileToRead = files[0];
      let context = this;
      let fileReader = new FileReader();
      fileReader.onload = (function(theFile) {
        let cont = context;
        return function(e) {
          cont.onFileLoad(e, cont);
          console.log(e);
          console.log(cont);
        };
      })(context);

      fileReader.readAsText(fileToRead, "UTF-8");
    }

  }
}
