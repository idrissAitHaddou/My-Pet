import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable()
export class TokenAccessInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.authService.loadToken();
    if (token) {
      // we set it to the header
      request = request.clone({
        headers: request.headers.set('Authorization', 'Bearer '+token)
      });
    }
    return next.handle(request).pipe(
      catchError((error) => {
        if (error instanceof HttpErrorResponse) {
          if(error.status === 401) {
                this.authService.logout()
          }
        }
        return throwError(error);
      })
    )
  }
}
