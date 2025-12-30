import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecLayoutComponent } from './exec-layout.component';

describe('ExecLayoutComponent', () => {
  let component: ExecLayoutComponent;
  let fixture: ComponentFixture<ExecLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExecLayoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExecLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
