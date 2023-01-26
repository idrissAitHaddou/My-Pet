import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersAccountInfoComponent } from './components/users-account-info/users-account-info.component';
import { UsersChangePasswordComponent } from './components/users-change-password/users-change-password.component';

import { ReactiveFormsModule } from '@angular/forms';
import { AlertsModule } from '../alerts/alerts.module';


@NgModule({
  declarations: [
    UsersAccountInfoComponent,
    UsersChangePasswordComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    ReactiveFormsModule,
    AlertsModule
  ]
})
export class UsersModule { }
