import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmincoursedetailComponent } from './admincoursedetail.component';

describe('AdmincoursedetailComponent', () => {
  let component: AdmincoursedetailComponent;
  let fixture: ComponentFixture<AdmincoursedetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdmincoursedetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdmincoursedetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
