import { TestBed } from '@angular/core/testing';

import { PublicListsResolverService } from './public-lists-resolver.service';

describe('ListsResolverService', () => {
  let service: PublicListsResolverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PublicListsResolverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
