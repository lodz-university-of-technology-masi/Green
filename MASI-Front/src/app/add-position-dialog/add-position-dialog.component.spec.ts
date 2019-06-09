import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPositionDialogComponent } from './add-position-dialog.component';

describe('AddPositionDialogComponent', () => {
  let component: AddPositionDialogComponent;
  let fixture: ComponentFixture<AddPositionDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPositionDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPositionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
