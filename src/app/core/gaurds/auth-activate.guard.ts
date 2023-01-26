import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, of, switchMap } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthActivateGuard implements CanActivate {
  
  constructor(private authService: AuthService, private _router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      return this.checkAuthentication()
  }

  checkAuthentication(): Observable<boolean> {
    return this.authService.checkAuth().pipe(
      switchMap((authentication: boolean) => {
          if(authentication) {
            return of(true)
          } 
          this.authService.logoutWithGuard()
          this._router.navigate(["/"])
          return of(false)
      })
    )
}
  
}
