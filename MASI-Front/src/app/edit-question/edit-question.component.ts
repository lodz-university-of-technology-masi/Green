import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormControl} from '@angular/forms';

export interface EditQuestionData {
  id: number;
  test: number;
  version: number;
  type: string;
  description: string;
  option1?: string;
  option2?: string;
  option3?: string;
  minVal?: number;
  maxVal?: number;
}

@Component({
  selector: 'app-edit-question',
  templateUrl: './edit-question.component.html',
  styleUrls: ['./edit-question.component.css']
})
export class EditQuestionComponent {
  options = [{key: 'OPEN', show: 'Open type'},
    {key: 'CHOOSE', show: 'Choose type'},
    {key: 'SCALE', show: 'Scale type'},
    {key: 'Number', show: 'Number type'}];
  constructor(
    public dialogRef: MatDialogRef<EditQuestionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: EditQuestionData) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
