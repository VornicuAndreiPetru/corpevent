import { TestBed } from '@angular/core/testing';

import { InternalEventService } from './internal-event.service';

describe('InternalEventService', () => {
  let service: InternalEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InternalEventService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
