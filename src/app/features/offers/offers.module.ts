import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OffersRoutingModule } from './offers-routing.module';
import { OffersCreateComponent } from './components/offers-create/offers-create.component';
import { OffersDetailsComponent } from './components/offers-details/offers-details.component';
import { OffersUpdateComponent } from './components/offers-update/offers-update.component';
import { OffersCardComponent } from './components/offers-card/offers-card.component';
import { AlertsModule } from '../alerts/alerts.module';

import { ReactiveFormsModule } from '@angular/forms';
import { DescriptionPipe } from './pipes/description.pipe';


@NgModule({
  declarations: [
    OffersCreateComponent,
    OffersDetailsComponent,
    OffersUpdateComponent,
    OffersCardComponent,
    DescriptionPipe,
  ],
  imports: [
    CommonModule,
    OffersRoutingModule,
    AlertsModule,
    ReactiveFormsModule
  ],
  exports: [
    OffersDetailsComponent,
    OffersCardComponent
  ]
})
export class OffersModule { }
