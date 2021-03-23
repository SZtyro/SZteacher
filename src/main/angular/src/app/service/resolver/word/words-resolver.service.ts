import { Injectable } from '@angular/core';
import {HttpService} from "../../http.service";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WordsResolverService implements Resolve<any>{

  constructor(
    private http: HttpService
  ) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
    return this.http.getWordsFromList(route.params['id']);
  }
}
