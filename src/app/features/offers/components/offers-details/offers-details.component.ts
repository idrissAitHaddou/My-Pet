import { Component, Input, OnInit } from '@angular/core';
import { CurrentUser } from 'src/app/features/users/interfaces/users';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-offers-details',
  templateUrl: './offers-details.component.html',
  styleUrls: ['./offers-details.component.css']
})
export class OffersDetailsComponent implements OnInit {
  @Input() jobDetails: any = [] ;
  currentUser: CurrentUser = new CurrentUser()
  urlHostingImages: string = ""
  constructor() {
    this.urlHostingImages = environment.urlHostingImages
   }

  ngOnInit(): void {
  }

}
