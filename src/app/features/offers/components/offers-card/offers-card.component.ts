import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { CurrentUser } from 'src/app/features/users/interfaces/users';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-offers-card',
  templateUrl: './offers-card.component.html',
  styleUrls: ['./offers-card.component.css']
})
export class OffersCardComponent implements OnInit {
  @Input() job: any
  @Output() someEvent = new EventEmitter()
  currentUser: CurrentUser = new CurrentUser();
  urlHostingImages: string = ""
  constructor(private authService: AuthService) {
    this.urlHostingImages = environment.urlHostingImages
    this.currentUser = this.authService.currentUser
   }

  ngOnInit(): void {
  }

  checkIsCommted(comments: any): boolean {
    if(comments.length > 0 && this.currentUser) {
      const userCommeted = comments.find((comment: any) => comment.user.email === this.currentUser.email)
      if(userCommeted) return true
      return false
    }
    return false
  }

}
