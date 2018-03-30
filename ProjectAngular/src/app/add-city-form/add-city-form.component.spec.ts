import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCityFormComponent } from './add-city-form.component';

describe('AddCityFormComponent', () => {
  let component: AddCityFormComponent;
  let fixture: ComponentFixture<AddCityFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCityFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCityFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
