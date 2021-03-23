import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private static readonly prefix: String = 'api/'

  constructor(private http: HttpClient) {
  }

  //POST

  saveWord(body) {
    return this.http.post(HttpService.prefix + 'word', body);
  }

  saveWordList(body) {
    return this.http.post(HttpService.prefix + 'list', body);
  }

  addWordToList(body, listId) {
    return this.http.post(HttpService.prefix + 'list/' + listId + '/word', body);
  }

  //GET

  getWords(filter, language?, translationLanguage?) {
    let params = {filter: filter};

    if (language)
      params['language'] = language;
    if (translationLanguage)
      params['translationLanguage'] = translationLanguage;

    return this.http.get<any[]>(HttpService.prefix + 'word', {params: params});
  }

  getLanguages() {
    return this.http.get(HttpService.prefix + 'language');
  }

  getWordList(id) {
    return this.http.get<any[]>(HttpService.prefix + "list/" + id);
  }

  getWordsFromList(id) {
    return this.http.get<any[]>(HttpService.prefix + "list/" + id + '/word');
  }

  getAllPublicLists(){
    return this.http.get<any[]>(HttpService.prefix + "list/public");
  }

  getAllPrivateLists(){
    return this.http.get<any[]>(HttpService.prefix + "list/private");
  }

  //DELETE

  deleteWordFromList(listId, wordId) {
    return this.http.delete(HttpService.prefix + "list/" + listId + '/word/' + wordId);
  }

}
