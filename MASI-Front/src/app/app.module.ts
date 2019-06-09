import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MyMaterialModule } from  './material.module';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {RegisterUserToast, RegistrationComponentComponent} from './registration-component/registration-component.component';
import { LoginComponentComponent } from './login-component/login-component.component';
import {TESTSComponent} from './tests/tests.component';
import {ChangeStatusToast, POSITIONSComponent} from './positions/positions.component';
import {USERSComponent} from './users/users.component';
import {UserService} from './shared/user.service';
import {ModService} from './shared/mod.service';
import {AuthGuard} from './auth/auth.guard';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from './auth/auth.interceptor';
import {fakeBackendProvider} from './shared/FakeBackendInterceptor';
import { ToastrModule } from 'ngx-toastr';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {
  MatButtonModule,
  MatCheckboxModule, MatDialogModule,
  MatExpansionModule,
  MatGridListModule,
  MatOptionModule,
  MatSelectModule, MatSnackBarModule
} from '@angular/material';
import {HomeComponent} from './home/home.component';
import { MainPageComponent } from './main-page/main-page.component';
import { EditUserDialogComponent } from './edit-user-dialog/edit-user-dialog.component';
import {PositionService} from './shared/position.service';
import { AddPositionDialogComponent } from './add-position-dialog/add-position-dialog.component';
import {TestsService} from './shared/tests.service';
import { EditQuestionComponent } from './edit-question/edit-question.component';
import { AddQuestionComponent } from './add-question/add-question.component';
import { AddTestDialogComponent } from './add-test-dialog/add-test-dialog.component';
import { CandidatePanelComponent } from './candidate-panel/candidate-panel.component';




@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponentComponent,
    LoginComponentComponent,
    USERSComponent,
    POSITIONSComponent,
    TESTSComponent,
    HomeComponent,
    MainPageComponent,
    EditUserDialogComponent,
    ChangeStatusToast,
    RegisterUserToast,
    AddPositionDialogComponent,
    EditQuestionComponent,
    AddQuestionComponent,
    AddTestDialogComponent,
    CandidatePanelComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MyMaterialModule,
    FormsModule,
    HttpClientModule,
    MatExpansionModule,
    MatButtonModule,
    MatCheckboxModule,
    MatOptionModule,
    MatSelectModule,
    MatGridListModule,
    MatDialogModule,
    MatSnackBarModule,
    ToastrModule.forRoot(),
    RouterModule.forRoot([
      { path: 'main', component: MainPageComponent, children: [{
        path: 'home', component: HomeComponent, canActivate: [AuthGuard],
          children: [
            { path: 'users', component: USERSComponent},
            { path: 'positions', component: POSITIONSComponent},
            { path: 'tests', component: TESTSComponent},
            { path: 'candidatePanel', component: CandidatePanelComponent}
          ]
        },
          { path: 'register', component: RegistrationComponentComponent },
          { path: 'login', component: LoginComponentComponent },
        ] },
      { path: '', redirectTo: 'main', pathMatch: 'full' },

    ]),

  ],
  entryComponents: [
    EditUserDialogComponent,
    AddPositionDialogComponent,
    ChangeStatusToast,
    RegisterUserToast,
    EditQuestionComponent,
    AddQuestionComponent,
    AddTestDialogComponent
  ],
  providers: [
    UserService,
    ModService,
    PositionService,
    AuthGuard,
    TestsService,
    {
      provide : HTTP_INTERCEPTORS,
      useClass : AuthInterceptor,
      multi : true
    },
    fakeBackendProvider,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

