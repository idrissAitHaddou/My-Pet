import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { User } from 'src/app/features/users/interfaces/users';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FormAlertComponent } from 'src/app/features/alerts/components/form-alert/form-alert.component';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  @ViewChild(FormAlertComponent) alertComponent: FormAlertComponent = new FormAlertComponent()
  user: User = new User();
  alertMessage: string = "";
  alertColor: string = "";
  loading: boolean = false;
  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });
  constructor( private authService: AuthService, private router: Router) { 
   }

  ngOnInit(): void {
  }

  login(form: any): void {
      this.user = form.value;
      if(this.loginForm.valid) {
          localStorage.clear()
          this.loading = true;
          this.authService.signIn(this.user).subscribe( (responce: any) => {
              this.loading = false;
              if(!responce) {
                  this.alertMessage = "Login failed"
                  this.alertColor = "red"
                  this.alertComponent.makeAlertStyle(this.alertColor, this.alertMessage)
                  return
              }
              this.router.navigate(['/']);
              history.go(0);
              window.location.reload()
          })
    }
    else {
        this.alertMessage = "All information requered"
        this.alertColor = "red"
        this.alertComponent.makeAlertStyle(this.alertColor, this.alertMessage)
    }
    
  }

}
