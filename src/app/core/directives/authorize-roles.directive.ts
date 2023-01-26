import { Directive, ElementRef} from '@angular/core';
import { AuthService } from '../services/auth.service';

@Directive({
  selector: '[appAuthorizeRoles]'
})
export class AuthorizeRolesDirective {

  constructor(
    private _el: ElementRef,
    private authService: AuthService
  ) { }

  ngAfterViewInit(): void {
    const currentRole = this.authService.currentUser.role
    currentRole && currentRole == "user" ? this._el.nativeElement.remove() : ""
  }

}
