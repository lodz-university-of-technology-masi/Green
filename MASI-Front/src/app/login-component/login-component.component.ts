import { Component, OnInit } from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {UserService} from '../shared/user.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {
  isLoginError = false;
  formGroup: FormGroup;
  constructor(private userService: UserService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.createForm();
  }
  OnSubmit(form) {
    this.userService.userAuthentication(form.value.email, form.value.pass).subscribe((data: any) => {
        localStorage.setItem('userToken', data.token);
        this.router.navigate(['/']);
      },
      (err: HttpErrorResponse) => {
        this.isLoginError = true;
      });
  }
  createForm() {
    this.formGroup = this.formBuilder.group({
      'email': [null, [Validators.required]],
      'password': [null, [Validators.required]],
      'validate': ''
    });
  }
}
