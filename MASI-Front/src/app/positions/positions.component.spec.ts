import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { POSITIONSComponent } from './positions.component';

describe('POSITIONSComponent', () => {
  let component: POSITIONSComponent;
  let fixture: ComponentFixture<POSITIONSComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ POSITIONSComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(POSITIONSComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
