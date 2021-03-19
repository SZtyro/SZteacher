import { TestBed } from '@angular/core/testing';

import { ListResolverService } from './list-resolver.service';

describe('ListResolverService', () => {
  let service: ListResolverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListResolverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
