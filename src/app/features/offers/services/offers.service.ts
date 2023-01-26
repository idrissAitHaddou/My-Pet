import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Jobs } from '../interfaces/jobs';

@Injectable({
  providedIn: 'root'
})
export class OffersService {

  constructor(private http: HttpClient) { }

  getAllOffers(): Observable<any[]> {
    return this.http.get<any[]>(environment.baseApi + "offer/get/all")
  }
  
  getAllOffersByTitle(title: string): Observable<any[]> {
    return this.http.get<any[]>(environment.baseApi + "offer/get/by-title?title="+title) 
  }

  createOffer(job: Jobs): Observable<boolean> {
    return this.http.post<boolean>(environment.baseApi + "offer/add", job)
  }

}
