import { Component, OnInit, Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

export interface UserDialogData {
  name: string;
  email: string;
  role: string;
}

@Component({
  selector: 'app-edit-user-dialog',
  templateUrl: './edit-user-dialog.component.html',
  styleUrls: ['./edit-user-dialog.component.css']
})
export class EditUserDialogComponent {
  roles = [{key: 'RED', show: 'Redactor'}, {key: 'CAN', show: 'Candidate'}];

  constructor(
    public dialogRef: MatDialogRef<EditUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: UserDialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
