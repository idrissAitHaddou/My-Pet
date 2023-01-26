import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommentsListComponent } from './components/comments-list/comments-list.component';
import { CommentsCreateComponent } from './components/comments-create/comments-create.component';

import { ReactiveFormsModule } from '@angular/forms';
import { AlertsModule } from '../alerts/alerts.module';



@NgModule({
  declarations: [
    CommentsListComponent,
    CommentsCreateComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AlertsModule,
  ],
  exports: [
    CommentsListComponent,
    CommentsCreateComponent,
  ]
})
export class CommentsModule { }
