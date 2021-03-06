import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {HttpService} from "../../http.service";

@Injectable({
  providedIn: 'root'
})
export class LanguageResolverService implements Resolve<any> {

  constructor(private http: HttpService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
    return this.http.getLanguages();
  }
}
