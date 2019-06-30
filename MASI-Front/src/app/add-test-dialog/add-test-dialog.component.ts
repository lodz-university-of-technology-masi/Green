import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormControl} from '@angular/forms';
import {AddQuestionData} from '../add-question/add-question.component';

export interface AddTestData {
  name: string;

}

@Component({
  selector: 'app-add-test-dialog',
  templateUrl: './add-test-dialog.component.html',
  styleUrls: ['./add-test-dialog.component.css']
})
export class AddTestDialogComponent  {

  constructor(
    public dialogRef: MatDialogRef<AddTestDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: AddTestData) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
