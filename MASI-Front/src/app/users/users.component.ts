import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../shared/user.service';
import {ModService} from '../shared/mod.service';
import {MatDialog} from '@angular/material';
import {EditUserDialogComponent} from '../edit-user-dialog/edit-user-dialog.component';

@Component({
  selector: 'masi-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class USERSComponent implements OnInit {
  userList: any;
  editedUser: any = {};
  activeEditWindow: any = 'none';

  public constructor(private router: Router, private userService: UserService, private modService: ModService,
                     public dialog: MatDialog) {
  }
  ngOnInit() {
    this.editedUser.Id = '';
    this.modService.getAllUsers().subscribe((users: any) => {
      this.userList = users.list;
      console.log(this.userList);
    });
  }

  EditUser(user): void {
    console.log(user);
    this.activeEditWindow = 'EditUser';
    this.editedUser.Id = user.Id;
    const dialogRef = this.dialog.open(EditUserDialogComponent, {
      width: '250px',
      data: {name: user.name, email: user.email, role: user.role}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.userService.editUser({name: result.name, role: result.role, email: result.email, id: user.id});
    });
  }
}
