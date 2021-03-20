import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WordListContentComponent } from './word-list-content.component';

describe('WordListContentComponent', () => {
  let component: WordListContentComponent;
  let fixture: ComponentFixture<WordListContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WordListContentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WordListContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
