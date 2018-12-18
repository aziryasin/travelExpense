import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TravelReimbursementsComponent } from './travel-reimbursements.component';

describe('TravelReimbursementsComponent', () => {
  let component: TravelReimbursementsComponent;
  let fixture: ComponentFixture<TravelReimbursementsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TravelReimbursementsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TravelReimbursementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
