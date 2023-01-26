import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersAccountInfoComponent } from './users-account-info.component';

describe('UsersAccountInfoComponent', () => {
  let component: UsersAccountInfoComponent;
  let fixture: ComponentFixture<UsersAccountInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersAccountInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsersAccountInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
