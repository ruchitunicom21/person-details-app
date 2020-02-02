import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,FormArray, FormBuilder } from '@angular/forms'
import {Person} from './person';
import { Subscription } from 'rxjs';
import {ApiCallService} from './api-call.service';



@Component({
  selector: 'app-person-details',
  templateUrl: './person-details.component.html',
  styleUrls: ['./person-details.component.css']
})
export class PersonDetailsComponent implements OnInit {

person:Person=null;
reponsePerson:any[]=[];
personArray:any[]=[];

hobbyArray: any= ['Swimming', 'Chess', 'Cricket', 'Football'];
 
 personDetailsForm = this.fb.group({
	 id:[''],
    first_name: [''],
    last_name: [''],
    age:[''],
	favorite_colour:[''],
	hobby:['']	,
	 detailsArray: this.fb.array([
    this.newDetails()
  ])
  });
  get detailsArray(): FormArray  {
  return this.personDetailsForm.get("detailsArray") as FormArray;
}
constructor(private fb: FormBuilder,private apiCall : ApiCallService) { }

  ngOnInit() {
  }
  newDetails(): FormGroup {
    return this.fb.group({
		id:'',
     first_name: '',
    last_name: '',
    age:'',
	favorite_colour:'',
	hobby:''	
    })
  }
 
  addDetailsArray() {
  this.detailsArray.push( this.newDetails());
  
}
deleteDetailsArray(i:number) {
  this.detailsArray.removeAt(i);
 
}
submit() {
	let temp=this.personDetailsForm.get("detailsArray") as FormArray
    
	for(let j=0;j<temp.value.length;j++){
	 this.person = new Person(temp.value[j]);
	 this.personArray.push(this.person);
	}
    this.apiCall.addPerson(this.personArray).subscribe(person => this.extractResponse(person))
    
    this.personArray=[];

  }

  extractResponse(person:any){
	  
	  this.reponsePerson.push(person);
	  let temp=this.personDetailsForm.get("detailsArray") as FormArray
	  
  }
}
