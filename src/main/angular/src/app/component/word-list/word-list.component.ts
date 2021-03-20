import {Component, HostListener, OnInit} from '@angular/core';
import {HttpService} from "../../service/http.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-word-list',
  templateUrl: './word-list.component.html',
  styleUrls: ['./word-list.component.scss']
})
export class WordListComponent implements OnInit {


  languages: any[];
  list;
  isMobile: boolean = window.innerWidth < 768;

  constructor(
    private http: HttpService,
    private route: ActivatedRoute,
    private router: Router
  ) {

  }

  ngOnInit(): void {
    this.route.data.subscribe(data => {
      this.languages = data.languages;
      this.list = data.list;
      if (!this.list)
        this.list = {private: true};
    });


  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    if (window.innerWidth > 768)
      this.isMobile = false;
    else
      this.isMobile = true;
  }


  save() {
    this.http.saveWordList(this.list).subscribe(
      success => {
        this.router.navigate(['list', success['id']])
      },
      error => {
        console.error(error);
      }
    )
  }


}
