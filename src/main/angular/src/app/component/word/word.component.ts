import {Component, OnInit} from '@angular/core';
import {Subject, timer} from "rxjs";
import {HttpService} from "../../service/http.service";
import {debounce} from "rxjs/operators";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-word',
  templateUrl: './word.component.html',
  styleUrls: ['./word.component.scss']
})
export class WordComponent implements OnInit {

  subject = new Subject();
  words: any[];
  word: String;
  translations: String[] = [];
  translation: String;
  languageId;
  translationLanguageId;
  languages;

  constructor(
    private http: HttpService,
    private route: ActivatedRoute
  ) {

  }

  ngOnInit(): void {
    this.route.data.subscribe(data => {
      this.languages = data.languages;
    });

    this.subject.pipe(debounce(() => timer(500))).subscribe(filter => {
      if (filter != null)
        this.http.getWords(filter).subscribe(words => {
          this.words = words;
        })
    })

  }

  search($event) {
    this.subject.next($event.target.value.toLowerCase())
  }

  save() {

    let body = {
      "languageId": this.languageId,
      "original": this.word,
      "translationLanguageId": this.translationLanguageId,
      "translation": this.translations
    }


    this.http.saveWord(body).subscribe(
      () => {
        this.word = null;
        this.languageId = null;
        this.translationLanguageId = null;
      },
      err => {
      }
    )
  }

  setWord(word) {

    this.languageId = word['language'].id;
    this.translationLanguageId = word['translationLanguage'].id;
    this.word = word['original'];
    this.translations = word['translation'].split(",")
  }

  addTranslation() {
    if (this.translation != null && this.translation != "") {
      this.translations.push(this.translation);
      this.translation = null;
    }
  }

  deleteTranslation(trnanslation) {
    this.translations.splice(this.translations.indexOf(trnanslation), 1);
  }
}
