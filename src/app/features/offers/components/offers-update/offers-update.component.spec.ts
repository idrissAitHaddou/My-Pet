import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OffersUpdateComponent } from './offers-update.component';

describe('OffersUpdateComponent', () => {
  let component: OffersUpdateComponent;
  let fixture: ComponentFixture<OffersUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OffersUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OffersUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
