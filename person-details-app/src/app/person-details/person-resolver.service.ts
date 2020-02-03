import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
}                                 from '@angular/router';
import { Observable, of, EMPTY }  from 'rxjs';
import { mergeMap, take }         from 'rxjs/operators';
import {Person} from './person';
import {ApiCallService} from './api-call.service';

@Injectable({
  providedIn: 'root'
})
export class PersonResolverService implements Resolve<Person>{

    constructor(private apiCall : ApiCallService, private router: Router) {}
	 resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Person>  {
   

    return this.apiCall.getPerson();
  }
	
}
