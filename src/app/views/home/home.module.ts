import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OffersModule } from 'src/app/features/offers/offers.module';
import { HomeComponent } from './home/home.component';
import { SearchModule } from 'src/app/features/search/search.module';
import { CommentsModule } from 'src/app/features/comments/comments.module';



@NgModule({
  declarations: [
      HomeComponent
  ],
  imports: [
    CommonModule,
    OffersModule,
    SearchModule,
    OffersModule,
    CommentsModule,
  ]
})
export class HomeModule { }
