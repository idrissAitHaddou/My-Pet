import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-search-offers',
  templateUrl: './search-offers.component.html',
  styleUrls: ['./search-offers.component.css']
})
export class SearchOffersComponent implements OnInit {
  @Output() serachValue = new EventEmitter<any>()
  constructor() { }

  ngOnInit(): void {
  }

}
