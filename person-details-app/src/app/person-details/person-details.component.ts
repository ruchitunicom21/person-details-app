import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,FormArray, FormBuilder } from '@angular/forms'
import {Person} from './person';
import { Subscription } from 'rxjs';
import {ApiCallService} from './api-call.service';
import { ActivatedRoute, Router } from '@angular/router';



@Component({
  selector: 'app-person-details',
  templateUrl: './person-details.component.html',
  styleUrls: ['./person-details.component.css']
})
export class PersonDetailsComponent implements OnInit {

person:Person=null;
reponsePersonList:Person[]=[];
responseMessage:string[]=[];
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
constructor(private fb: FormBuilder,private apiCall : ApiCallService, private route: ActivatedRoute,
    private router: Router,) { }

  ngOnInit() {
	   this.route.data.subscribe(person => this.extractResponse(person));
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
	let temp=this.personDetailsForm.get("detailsArray") as FormArray
    let selectedPersonId=temp.value[i].id;
	this.apiCall.deletePerson(selectedPersonId).subscribe(
	message => 
	{this.detailsArray.removeAt(i); 
	this.responseMessage=<string[]>message;
	if(this.reponsePersonList!=undefined && this.reponsePersonList.length>0){
	for(let i=0;i<this.reponsePersonList.length;i++){
		if(this.reponsePersonList[i]["personId"] == selectedPersonId){
			this.reponsePersonList.splice(i,1);
			
		}
	}}});
  
 
}
submit() {
	let temp=this.personDetailsForm.get("detailsArray") as FormArray
    
	for(let j=0;j<temp.value.length;j++){
	 this.person = new Person(temp.value[j]);
	 this.personArray.push(this.person);
	}
    this.apiCall.addPerson(this.personArray).subscribe(person => this.extractResponse(person))
    
    this.personArray=[];
    this.responseMessage=[];
   for(let j=0;j<this.detailsArray.length;j++){
	     this.detailsArray.removeAt(j);
   }
 
  }
  updatePerson(){
	  let temp=this.personDetailsForm.get("detailsArray") as FormArray
       let id="";
	for(let j=0;j<temp.value.length;j++){
		this.person = new Person(temp.value[j]);
		let propNames = Object.getOwnPropertyNames(this.person);
		for (var i = 0; i < propNames.length; i++) {
    let propName = propNames[i];
		if (this.person[propName] === null || this.person[propName] === undefined || this.person[propName]==="") {
      delete this.person[propName];
    }
  }
	 this.personArray.push(this.person);
	 if(j<temp.value.length-1){
	 id=id+temp.value[j].id+",";
	 }else{
		id= id+temp.value[j].id;
	 }
	 
	}
	this.apiCall.updatePerson(this.personArray,id).subscribe(message => 
	{ 
	this.responseMessage=<string[]>message;
	 this.apiCall.getPerson().subscribe(person => {
		 this.reponsePersonList=[];
		 this.extractResponse(person)});
	 })
    
    this.personArray=[];
	  
  }

  extractResponse(person:any){
	  if(person.person!=undefined && person instanceof Object){
	  for(let i=0;i<person.person.length;i++){
		    this.reponsePersonList.push(person.person[i]);
	  }
	  }else{
		for(let i=0;i<person.length;i++){
		    this.reponsePersonList.push(person[i]);
	  }  
		  
	  }
	
	  
  }
}
