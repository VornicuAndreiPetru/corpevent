import { TestBed } from '@angular/core/testing';

import { SefService } from './sef.service';

describe('SefService', () => {
  let service: SefService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SefService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
