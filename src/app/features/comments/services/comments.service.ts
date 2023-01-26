import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comments } from '../interfaces/comments';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private http: HttpClient) { }

  createComment(email: string, idJob: number, comment: Comments): Observable<boolean> {
    return this.http.post<boolean>(environment.baseApi + "comment/create?email=" + email+"&idJob=" + idJob, comment)
  }

}
