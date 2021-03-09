import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  prefix: String = 'api/'

  constructor(private http: HttpClient) {
  }

  getWords(filter) {
    return this.http.get<any[]>(this.prefix + 'word', {params: {filter: filter}});
  }

  saveWord(body) {
    return this.http.post(this.prefix + 'word', body);
  }

  getLanguages(){
    return this.http.get(this.prefix + 'language');
  }
}
