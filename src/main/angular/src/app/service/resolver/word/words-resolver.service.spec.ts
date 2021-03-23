import { TestBed } from '@angular/core/testing';

import { WordsResolverService } from './words-resolver.service';

describe('WordsResolverService', () => {
  let service: WordsResolverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WordsResolverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
