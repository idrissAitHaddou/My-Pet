import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthorizeRolesDirective } from './directives/authorize-roles.directive';
import { EmailPipe } from './pipes/email.pipe';



@NgModule({
  declarations: [
    AuthorizeRolesDirective,
    EmailPipe,
  ],
  imports: [
    CommonModule,
  ],
  exports: [
    EmailPipe,
    AuthorizeRolesDirective,
  ]
})
export class CoreModule { }
