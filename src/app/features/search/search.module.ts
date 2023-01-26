import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchOffersComponent } from './components/search-offers/search-offers.component';



@NgModule({
  declarations: [
    SearchOffersComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    SearchOffersComponent
  ]
})
export class SearchModule { }
