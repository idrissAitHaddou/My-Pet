import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DropdownLinksComponent } from './dropdown-links/dropdown-links.component';

import {MatMenuModule} from '@angular/material/menu';
import { CoreModule } from 'src/app/core/core.module';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    DropdownLinksComponent
  ],
  imports: [
    CommonModule,
    MatMenuModule,
    CoreModule,
    RouterModule
  ],
  exports: [
    DropdownLinksComponent
  ]
})
export class DropdownModule { }
