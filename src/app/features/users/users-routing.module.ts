import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersAccountInfoComponent } from './components/users-account-info/users-account-info.component';
import { UsersChangePasswordComponent } from './components/users-change-password/users-change-password.component';
import { UserAccountResolver } from './resolvers/user-account.resolver';

const routes: Routes = [
  {
    path: "info",
    component: UsersAccountInfoComponent,
    resolve: { userInfo: UserAccountResolver }
  },
  {
    path: "change-password",
    component: UsersChangePasswordComponent
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
