import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { FormAlertComponent } from 'src/app/features/alerts/components/form-alert/form-alert.component';
import { environment } from 'src/environments/environment';
import { CurrentUser } from '../../interfaces/users';
import { UsersService } from '../../services/users.service';

@Component({
  selector: 'app-users-account-info',
  templateUrl: './users-account-info.component.html',
  styleUrls: ['./users-account-info.component.css']
})
export class UsersAccountInfoComponent implements OnInit {
    @ViewChild(FormAlertComponent) alertComponent: FormAlertComponent = new FormAlertComponent()
    currentUser: CurrentUser = new CurrentUser()
    accountForm = new FormGroup({
      id: new FormControl(0, [Validators.required]),
      email: new FormControl('', [Validators.required]),
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      description: new FormControl("", [Validators.required]),
      image: new FormControl("", [Validators.required]),
    });
    alertMessage: string = ""
    alertColor: string = ""
    urlHostingImages: string = ""
  constructor(private userService: UsersService, private authService: AuthService, private activatedRouter: ActivatedRoute) {
    this.currentUser = this.authService.currentUser
    this.urlHostingImages = environment.urlHostingImages
   }

  ngOnInit(): void {
    this.getUserInfo()
  }

  getUserInfo(): void {
    this.activatedRouter.data.subscribe((user: any) => {
        this.accountForm.setValue({
          id: user.userInfo.id,
          email: user.userInfo.email,
          firstName: user.userInfo.firstName,
          lastName: user.userInfo.lastName,
          description: user.userInfo.description,
          image: user.userInfo.image,
        })
    })
  }

  updateUserAccount(form: any): void {
    if(this.accountForm.valid) {
      this.userService.updateUserAccount(form.value).subscribe(
        (responce: Boolean) => {
            if(responce) {
                    this.alertMessage = "Account Updated successfully"
                    this.alertColor = "green"
                    this.alertComponent.makeAlertStyle(this.alertColor, this.alertMessage)
            }else {
              this.alertMessage = "Account Updated faild"
              this.alertColor = "red"
              this.alertComponent.makeAlertStyle(this.alertColor, this.alertMessage)
            }
      })
      return
    }
    this.alertMessage = "All informations are required"
    this.alertColor = "red"
    this.alertComponent.makeAlertStyle(this.alertColor, this.alertMessage)
  }

}
