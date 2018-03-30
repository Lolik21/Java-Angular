import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBusFormComponent } from './add-bus-form.component';

describe('AddBusFormComponent', () => {
  let component: AddBusFormComponent;
  let fixture: ComponentFixture<AddBusFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddBusFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBusFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
