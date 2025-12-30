import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SefLayoutComponent } from './sef-layout.component';

describe('SefLayoutComponent', () => {
  let component: SefLayoutComponent;
  let fixture: ComponentFixture<SefLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SefLayoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SefLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
