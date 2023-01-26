import { Component, OnInit, ViewChild, Input, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';
import { FormAlertComponent } from 'src/app/features/alerts/components/form-alert/form-alert.component';
import { CurrentUser } from 'src/app/features/users/interfaces/users';
import { Comments } from '../../interfaces/comments';
import { CommentsService } from '../../services/comments.service';

@Component({
  selector: 'app-comments-create',
  templateUrl: './comments-create.component.html',
  styleUrls: ['./comments-create.component.css'],
}) 
export class CommentsCreateComponent implements OnInit {
  @ViewChild(FormAlertComponent) alertComponent: FormAlertComponent = new FormAlertComponent()
  @Input() jobDetails: any = [] ;
  @Output() incrementComments = new EventEmitter<Comments>();
  commentForm = new FormGroup({
    comment: new FormControl('', [Validators.required]),
  });
  currentUser: CurrentUser = new CurrentUser();
  newComment: Comments = new Comments()
  alertMessage: string = "";
  alertColor: string = "";
  constructor(private authService: AuthService, private commentsServices: CommentsService) { 
    this.currentUser = this.authService.currentUser
   }

  ngOnInit(): void {
  }

  createComment(form: any): void {
      if(!this.commentForm.valid) {
                this.alertMessage = "Comment is required"
                this.alertColor = "red"
                this.alertComponent.makeAlertStyle(this.alertColor, this.alertMessage)
                return
      }
      this.newComment.comment = form.get("comment")?.value
      this.newComment.user = this.currentUser
      this.commentsServices.createComment(this.currentUser.email, this.jobDetails.id, this.newComment).subscribe((responce: boolean) => {
        if(responce) {
          this.alertMessage = "Comment created successfully"
          this.alertColor = "green"
          this.alertComponent.makeAlertStyle(this.alertColor, this.alertMessage)
          this.incrementCountComments(this.newComment)
        return
      }
          this.alertMessage = "Your comment faild"
          this.alertColor = "red"
          this.alertComponent.makeAlertStyle(this.alertColor, this.alertMessage)
    })
  }

  incrementCountComments(comment: Comments): void {
    this.incrementComments.emit(comment)
    this.commentForm.setValue({comment: ""})
  }

}
