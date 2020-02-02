import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import {Person} from './person';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class ApiCallService {

handleErrorObservable (error: Response | any) {
  console.error(error.message || error);
  return Observable.throw(error.message || error);
} 
  constructor(private http: HttpClient) { }
  
  public addPerson(person: Person[]):Observable<Person>{
	  let objArray={"person":person};
	  return this.http.post<Person>("/api/persons/", objArray, httpOptions)
    .pipe(
      catchError(this.handleErrorObservable)
    );
  }
}
