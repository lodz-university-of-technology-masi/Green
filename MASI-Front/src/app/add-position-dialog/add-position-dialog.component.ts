import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {UserDialogData} from '../edit-user-dialog/edit-user-dialog.component';

export interface AddPositionDialogData {
  name: string;
  description: string;
  active: boolean;
}
@Component({
  selector: 'app-add-position-dialog',
  templateUrl: './add-position-dialog.component.html',
  styleUrls: ['./add-position-dialog.component.css']
})
export class AddPositionDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<AddPositionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: AddPositionDialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
