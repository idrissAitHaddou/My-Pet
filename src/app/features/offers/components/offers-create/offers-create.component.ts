import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';
import { FormAlertComponent } from 'src/app/features/alerts/components/form-alert/form-alert.component';
import { CurrentUser } from 'src/app/features/users/interfaces/users';
import { OffersService } from '../../services/offers.service';

@Component({
  selector: 'app-offers-create',
  templateUrl: './offers-create.component.html',
  styleUrls: ['./offers-create.component.css']
})
export class OffersCreateComponent implements OnInit {
    @ViewChild(FormAlertComponent) alertComponent: FormAlertComponent = new FormAlertComponent()
    currentUser: CurrentUser = new CurrentUser()
    uploadImage: any = []
    offerForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    campany: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    imagesBase: new FormControl("", [Validators.required]),
    emailCurrentuser: new FormControl("", [Validators.required]),
  });
  alertMessage: string = ""
  alertColor: string = ""
  constructor(private authService: AuthService, private offerService: OffersService) { 
    this.currentUser = this.authService.currentUser
   }

  ngOnInit(): void {
  }

  createOffer(form: any): void {
    this.offerForm.patchValue({imagesBase: this.uploadImage});
    this.offerForm.patchValue({emailCurrentuser: this.currentUser.email});
    if(this.offerForm.valid) {
      this.offerService.createOffer(form.value).subscribe(
        (responce: Boolean) => {
            if(responce) {
                    this.alertMessage = "Offer created successfully"
                    this.alertColor = "green"
                    this.alertComponent.makeAlertStyle(this.alertColor, this.alertMessage)
                    this.offerForm.reset()
            }else {
              this.alertMessage = "Offer created faild"
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

  uploadImages(event: any): void {
    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event) => {
      if (reader.result) {
        this.uploadImage[0] = reader.result.toString()
      }
  }
}

}
