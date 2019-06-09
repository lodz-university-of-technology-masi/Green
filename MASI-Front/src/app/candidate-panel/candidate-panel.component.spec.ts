import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatePanelComponent } from './candidate-panel.component';

describe('CandidatePanelComponent', () => {
  let component: CandidatePanelComponent;
  let fixture: ComponentFixture<CandidatePanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidatePanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidatePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
