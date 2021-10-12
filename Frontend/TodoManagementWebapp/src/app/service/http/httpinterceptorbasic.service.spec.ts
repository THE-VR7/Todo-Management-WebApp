import { TestBed } from '@angular/core/testing';

import { HttpinterceptorbasicService } from './httpinterceptorbasic.service';

describe('HttpinterceptorbasicService', () => {
  let service: HttpinterceptorbasicService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpinterceptorbasicService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
