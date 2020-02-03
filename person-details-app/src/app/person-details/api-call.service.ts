import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import {Person} from './person';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
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
	  return this.http.post<Person>(environment.apiUrl+"/persons/", objArray, httpOptions)
    .pipe(
      catchError(this.handleErrorObservable)
    );
  }
  
  getPerson() {
  // now returns an Observable of Config
  return this.http.get<Person>(environment.apiUrl+'/persons');
}
deletePerson (id: number){
  const url = `/persons/${id}`; // DELETE api/heroes/42
  return this.http.delete(environment.apiUrl+url, httpOptions)
    .pipe(
      catchError(this.handleErrorObservable)
    );
}
updatePerson (person: Person[], ids:string) {
	const url = `/persons/${ids}`;
	 let objArray={"person":person};
  return this.http.put<Person>(environment.apiUrl+url, objArray,httpOptions)
    .pipe(
      catchError(this.handleErrorObservable)
    );
}
}
