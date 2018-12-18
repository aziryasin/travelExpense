import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancesGivenComponent } from './advances-given.component';

describe('AdvancesGivenComponent', () => {
  let component: AdvancesGivenComponent;
  let fixture: ComponentFixture<AdvancesGivenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvancesGivenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancesGivenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
