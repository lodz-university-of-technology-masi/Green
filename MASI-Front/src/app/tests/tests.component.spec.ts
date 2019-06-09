import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TESTSComponent } from './tests.component';

describe('TESTSComponent', () => {
  let component: TESTSComponent;
  let fixture: ComponentFixture<TESTSComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TESTSComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TESTSComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
