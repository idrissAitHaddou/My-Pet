import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OffersCreateComponent } from './components/offers-create/offers-create.component';
import { OffersUpdateComponent } from './components/offers-update/offers-update.component';

const routes: Routes = [
  {
    path: "create",
    component: OffersCreateComponent
  },
  {
    path: "update",
    component: OffersUpdateComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OffersRoutingModule { }
