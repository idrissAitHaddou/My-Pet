import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CurrentUser } from '../interfaces/users';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) { }

  getUserAccount(id: number): Observable<CurrentUser> {
    return this.http.get<CurrentUser>(environment.baseApi + "user/profile?id=" + id)
  }

  updateUserAccount(user: CurrentUser): Observable<boolean> {
      return this.http.post<boolean>(environment.baseApi + "user/profile/update", user)
  }

}
