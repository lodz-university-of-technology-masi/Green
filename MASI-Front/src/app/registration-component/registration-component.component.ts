import { Component, OnInit } from '@angular/core';
import {User} from '../models/User.model';
import {UserService} from '../shared/user.service';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ChangeStatusToast} from '../positions/positions.component';
import {PositionService} from '../shared/position.service';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-registration-component',
  templateUrl: './registration-component.component.html',
  styleUrls: ['./registration-component.component.css']
})
export class RegistrationComponentComponent implements OnInit {
  user: User;
  formGroup: FormGroup;
  options = [{key: 'ENG', show: 'English'},{key: 'POL', show: 'Polski'} ];
  constructor(private userService: UserService, private formBuilder: FormBuilder, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.createForm();
    this.setChangeValidate();
  }

  createForm() {
    const emailregex: RegExp =
// tslint:disable-next-line:max-line-length
/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    this.formGroup = this.formBuilder.group({
      'email': [null, [Validators.required, Validators.pattern(emailregex)]],
      'name': [null, Validators.required],
      'password': [null, [Validators.required]],
      'language': [null, [Validators.required]],
      'validate': ''
    });
  }

  setChangeValidate() {
    this.formGroup.get('validate').valueChanges.subscribe(
      (validate) => {
        if (validate === '1') {
          this.formGroup.get('name').setValidators([Validators.required, Validators.minLength(3)]);
        } else {
          this.formGroup.get('name').setValidators(Validators.required);
        }
        this.formGroup.get('name').updateValueAndValidity();
      }
    );
  }
  OnSubmit(form) {
    this.userService.registerUser(form.value)
      .subscribe((data: any) => {
        if (data.message === 'OK') {
          this._snackBar.openFromComponent(RegisterUserToast, {
            duration: 5 * 1000,
          });
        }
      });
  }
  getErrorEmail() {
    return this.formGroup.get('email').hasError('required') ? 'Field is required' :
      this.formGroup.get('email').hasError('pattern') ? 'Not a valid emailaddress' :
        this.formGroup.get('email').hasError('alreadyInUse') ? 'This emailaddress is already in use' : '';
  }
  getErrorPassword() {
    return this.formGroup.get('password').hasError('required') ?
      'Field is required (at least eight characters, one uppercase letter and one number)' :
      this.formGroup.get('password').hasError('requirements') ?
        'Password needs to be at least eight characters, one uppercase letter and one number' : '';
  }
}

@Component({
  selector: 'registerUser-toast',
  template: `<span class="registerUser-toast">
               Registration Successful
              </span>`,
  styles: [`
    .registerUser-toast{
      color: white;
    }
  `],
})
export class RegisterUserToast  {}
