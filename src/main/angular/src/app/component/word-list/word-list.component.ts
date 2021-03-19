import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../service/http.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-word-list',
  templateUrl: './word-list.component.html',
  styleUrls: ['./word-list.component.scss']
})
export class WordListComponent implements OnInit {

  name: String;
  isPrivate: boolean;
  languageId: number;
  languages: any[];

  list;

  constructor(
    private http: HttpService,
    private route: ActivatedRoute
  ) {
    this.list = {};
  }

  ngOnInit(): void {
    this.route.data.subscribe(data => {
      this.languages = data.languages;
      this.list = data.list;
      if (!this.list)
        this.list = {}
    });
  }

  selectLanguage($event) {
    this.list.language = {}
    this.list.language['id'] = $event;
  }

  save() {
    this.http.saveWordList(this.list).subscribe()
  }
}
