import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {HttpService} from "../../http.service";

@Injectable({
  providedIn: 'root'
})
export class PrivateListsResolverService implements Resolve<any> {

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
    return this.http.getAllPrivateLists();
  }

  constructor(private http: HttpService) {
  }
}
