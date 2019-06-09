import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormControl} from '@angular/forms';

export interface AddQuestionData {
  type: string;
  description: string;
  options?: [];
  scale?: [];
}


@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent {
  options = [{key: 'OPEN', show: 'Open type'},
    {key: 'CHOOSE', show: 'Choose type'},
    {key: 'SCALE', show: 'Scale type'},
    {key: 'Number', show: 'Number type'}];
  descFormControl;
  constructor(
    public dialogRef: MatDialogRef<AddQuestionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: AddQuestionData) {
    this.descFormControl = new FormControl(data.description);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


}
