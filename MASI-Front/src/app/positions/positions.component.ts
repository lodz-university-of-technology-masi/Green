import { Component, OnInit} from '@angular/core';
import {PositionService} from '../shared/position.service';
import {ToastrService} from 'ngx-toastr';
import {MatDialog, MatSnackBar} from '@angular/material';
import {EditUserDialogComponent} from '../edit-user-dialog/edit-user-dialog.component';
import {AddPositionDialogComponent} from '../add-position-dialog/add-position-dialog.component';

@Component({
  selector: 'masi-positions',
  templateUrl: './positions.component.html',
  styleUrls: ['./positions.component.css']
})
export class POSITIONSComponent implements OnInit  {
  positionList: any;
  constructor(private positionService: PositionService, private _snackBar: MatSnackBar, public dialog: MatDialog) { }

  ngOnInit() {
    this.positionService.getAllPositions().subscribe(((positions: any) => {
      this.positionList = positions.response;
    }));
  }
  changeStatus(event, position) {
    this.positionService.editPosition({id: position.id, active: event.checked})
      .subscribe((data: any) => {
        if (data.message === 'OK') {
          this._snackBar.openFromComponent(ChangeStatusToast, {
            duration: 5 * 1000,
          });
        }
      });
  }
  AddPosition(): void {
    const dialogRef = this.dialog.open(AddPositionDialogComponent, {
      width: '250px',
      data: {name: '', active: false}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.positionService.addPosition({name: result.name, description: result.description, active: result.active})
        .subscribe(((we: any) => {
          this.positionService.getAllPositions().subscribe(((positions: any) => {
            this.positionList = positions.response;
          }));
        }));
    });
  }

}

@Component({
  selector: 'changeStatus-toast',
  template: `<span class="changeStatus-toast">
               Status changed successfully
              </span>`,
  styles: [`
    .changeStatus-toast{
      color: white;
    }
  `],
})
export class ChangeStatusToast  {}
