import { Component, OnInit } from '@angular/core';
import { Comments } from 'src/app/features/comments/interfaces/comments';
import { OffersService } from 'src/app/features/offers/services/offers.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  jobs: any = [];
  jobDetails: any = [];
  searchValue: string = ""
  constructor(private offersService: OffersService) { }

  ngOnInit(): void {
    this.getAllJobs()
  }

  getAllJobs(): void {
    this.offersService.getAllOffers().subscribe((data: any[]) => {
      this.jobs = data
      console.log(data);
    })
  }

  getJobDetails(event: any): void {
   this.jobDetails = event
  }

  searchJobs(event: any): void {
    let searchValue = event.target.value
    if(searchValue != "") {
      this.offersService.getAllOffersByTitle(event.target.value).subscribe((data: any[]) => {
        this.jobs = data
      })
      }else {
        this.getAllJobs()
      }
  }

  incrementCountComments(event: Comments): void {
      this.jobs.forEach((job: any) => {
        if(job.id == this.jobDetails.id) job.comments.push(event)
      });    
  }

}
