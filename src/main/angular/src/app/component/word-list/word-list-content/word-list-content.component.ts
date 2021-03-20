import {Component, HostListener, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {SelectionModel} from "@angular/cdk/collections";
import {Subject, timer} from "rxjs";
import {debounce} from "rxjs/operators";
import {HttpService} from "../../../service/http.service";

@Component({
  selector: 'app-word-list-content',
  templateUrl: './word-list-content.component.html',
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
/**
 * Lista słów
 */
export class WordListContentComponent implements OnInit {

  language = 'gb';
  translationLanguage = 'pl';

  word: String;
  dataSource: MatTableDataSource<any> = new MatTableDataSource<any>();
  displayedColumns = ['select', 'word'];
  expandedElement: any;
  selection = new SelectionModel<any>(true, []);
  subject = new Subject();
  words;

  constructor(
    private http: HttpService
  ) {

  }

  ngOnInit(): void {
    this.subject.pipe(debounce(() => timer(500))).subscribe(filter => {
      if (filter != null)
        this.http.getWords(filter, this.language, this.translationLanguage).subscribe(words => {
          this.words = words;
        })
    })
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: any): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.position + 1}`;
  }

  search($event) {
    this.subject.next($event.target.value.toLowerCase())
  }

  addWord(word) {
    this.dataSource.data.push(word)
    this.dataSource._updateChangeSubscription();
    this.word = "";
    this.words = null;
  }

}
