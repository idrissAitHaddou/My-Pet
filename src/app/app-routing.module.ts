import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthActivateGuard } from './core/gaurds/auth-activate.guard';
import { AuthDeactiveGuard } from './core/gaurds/auth-deactive.guard';
import { HomeComponent } from './views/home/home/home.component';
import { NotFoundComponent } from './views/not-found/not-found/not-found.component';

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
    children: [
        { path: "home", loadChildren: () => import("./views/home/home.module").then(m => m.HomeModule) }
    ]
  },
  {
    path: "offer",
    canActivate: [AuthActivateGuard],
    children: [ 
        { path: "" ,loadChildren: () => import("./features/offers/offers.module").then(m => m.OffersModule) }
     ]
  },
  {
    path: "account",
    canActivate: [AuthActivateGuard],
    children: [ 
        { path: "" ,loadChildren: () => import("./views/profile/profile.module").then(m => m.ProfileModule) }
     ]
  },
  {
    path: "register",
    canActivate: [AuthDeactiveGuard],
    children: [ 
        { path: "" ,loadChildren: () => import("./features/auth/auth.module").then(m => m.AuthModule) }
     ]
  },
  {
    path: "**",
    component: NotFoundComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
