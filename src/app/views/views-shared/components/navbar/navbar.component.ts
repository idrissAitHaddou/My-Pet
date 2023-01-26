import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { CurrentUser } from 'src/app/features/users/interfaces/users';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  currentUser: CurrentUser = new CurrentUser();
  _authenticated: boolean = false;
  urlHostingImages: string = ""
  constructor( private authService: AuthService ) { 
    this._authenticated = this.authService.authenticated
    this.currentUser = this.authService.currentUser
    this.urlHostingImages = environment.urlHostingImages
   }

  ngOnInit(): void {
  }

}
