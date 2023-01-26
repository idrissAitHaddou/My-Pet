import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { CurrentUser } from 'src/app/features/users/interfaces/users';

@Component({
  selector: 'app-dropdown-links',
  templateUrl: './dropdown-links.component.html',
  styleUrls: ['./dropdown-links.component.css']
})
export class DropdownLinksComponent implements OnInit {
  currentUser: CurrentUser = new CurrentUser();
  constructor(private authService: AuthService) {
    this.currentUser = this.authService.currentUser
   }

  ngOnInit(): void {
  }

  logout(): void {
    this.authService.logout()    
}

}
