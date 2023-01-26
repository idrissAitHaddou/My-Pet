import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { DropdownModule } from 'src/app/shared/dropdown/dropdown.module';



@NgModule({
  declarations: [
    FooterComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    DropdownModule
  ],
  exports: [
    FooterComponent,
    NavbarComponent,
  ]
})
export class ViewsSharedModule { }
