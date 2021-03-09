import {Component, OnInit} from '@angular/core';
import {Subject, timer} from "rxjs";
import {HttpService} from "./service/http.service";
import {debounce, debounceTime} from "rxjs/operators";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(
    private translate: TranslateService
  ) {
    translate.addLangs([ 'pl']);
    translate.setDefaultLang('pl');
    translate.use('pl')
  }


}
