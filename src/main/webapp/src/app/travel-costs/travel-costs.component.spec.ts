import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TravelCostsComponent } from './travel-costs.component';

describe('TravelCostsComponent', () => {
  let component: TravelCostsComponent;
  let fixture: ComponentFixture<TravelCostsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TravelCostsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TravelCostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
