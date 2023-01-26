import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of, catchError, switchMap } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth.service';
import { CurrentUser } from '../interfaces/users';
import { UsersService } from '../services/users.service';

@Injectable({
  providedIn: 'root'
})
export class UserAccountResolver implements Resolve<boolean> {
  currentUser: CurrentUser = new CurrentUser();
  constructor(private userService: UsersService, private authService: AuthService) {
    this.currentUser = this.authService.currentUser
  }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
   return this.userService.getUserAccount(this.currentUser.id).pipe(
      catchError((error: any) => { return of(null) } ),
      switchMap((responce: any) => { return of(responce) } )
    )
  }
}
