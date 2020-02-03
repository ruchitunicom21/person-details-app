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
  
   addPerson(person: Person[]):Observable<Person>{
	  let objArray={"person":person};
	  return this.http.post<Person>("/api/persons/", objArray, httpOptions)
    .pipe(
      catchError(this.handleErrorObservable)
    );
  }
  
  getPerson() {
  // now returns an Observable of Config
  return this.http.get<Person>('/api/persons');
}
deletePerson (id: number){
  const url = `/api/persons/${id}`; // DELETE api/heroes/42
  return this.http.delete(url, httpOptions)
    .pipe(
      catchError(this.handleErrorObservable)
    );
}
updatePerson (person: Person[], ids:string) {
	const url = `/api/persons/${ids}`;
	 let objArray={"person":person};
  return this.http.put<Person>(url, objArray,httpOptions)
    .pipe(
      catchError(this.handleErrorObservable)
    );
}
}
