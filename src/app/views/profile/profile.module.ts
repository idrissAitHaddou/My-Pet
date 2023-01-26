import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileUserComponent } from './profile-user/profile-user.component';
import { UsersModule } from 'src/app/features/users/users.module';


@NgModule({
  declarations: [
    ProfileUserComponent
  ],
  imports: [
    CommonModule,
    ProfileRoutingModule,
    UsersModule,
  ]
})
export class ProfileModule { }
