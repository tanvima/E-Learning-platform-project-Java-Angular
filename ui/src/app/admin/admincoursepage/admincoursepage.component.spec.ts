import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmincoursepageComponent } from './admincoursepage.component';

describe('AdmincoursepageComponent', () => {
  let component: AdmincoursepageComponent;
  let fixture: ComponentFixture<AdmincoursepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdmincoursepageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdmincoursepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
