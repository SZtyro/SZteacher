import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private static readonly prefix: String = 'api/'

  constructor(private http: HttpClient) {
  }


  saveWord(body) {
    return this.http.post(HttpService.prefix + 'word', body);
  }

  saveWordList(body) {
    return this.http.post(HttpService.prefix + 'list', body);
  }


  getWords(filter) {
    return this.http.get<any[]>(HttpService.prefix + 'word', {params: {filter: filter}});
  }

  getLanguages() {
    return this.http.get(HttpService.prefix + 'language');
  }

  getWordList(id) {
    return this.http.get(HttpService.prefix + "list/" + id);
  }
}
