package com.mycompany.personEntities.controller;

import com.mycompany.personEntities.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.mycompany.personEntities.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PersonEntities {

	@Autowired
    PersonServices personServices;

	@RequestMapping(value="/persons",method = RequestMethod.POST)
	public ResponseEntity<List<Person>> createPerson(@RequestBody Map<String, List <Person>> person)  {

		List<Person> list= null;
		ResponseEntity<List<Person>> responseEntity=null;
		try {
			list = personServices.save(person.get("person"));
		} catch (Exception e) {
			responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		responseEntity=new ResponseEntity<>(list,HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(value="/persons/{personIds}",method = RequestMethod.DELETE)
	public ResponseEntity<List<String>> deletePersons(@PathVariable List<Long> personIds)  {

		List<String> list=new ArrayList<>();
		ResponseEntity<List<String>> responseEntity=null;
		try {
			for(Long id:personIds) {
				list.add(personServices.delete(id));
			}
		} catch (Exception e) {
			responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		responseEntity=new ResponseEntity<>(list,HttpStatus.FORBIDDEN);
		return responseEntity;
	}
	@RequestMapping(value="/persons",method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getAllPerson()  {

		List<Person> list= null;
		ResponseEntity<List<Person>> responseEntity=null;
		try {
			list = personServices.getAllPerson();
		} catch (Exception e) {
			responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		responseEntity=new ResponseEntity<>(list,HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(value="/persons/{personIds}",method = RequestMethod.PUT)
	public ResponseEntity<List<String>> updatePersons(@RequestBody Map<String, List <Person>> person, @PathVariable List<Long> personIds)  {

		List<String> list=new ArrayList<>();
		ResponseEntity<List<String>> responseEntity=null;
		try {
			if(person.size()!=personIds.size()){
				list.add("Size of Request body and personIds are not matching");
				responseEntity=new ResponseEntity<>(list,HttpStatus.UNPROCESSABLE_ENTITY);
			}else {
				int i=0;
				for (Long id : personIds) {
					list .add(personServices.update(person.get("person").get(i), id));
					i++;
				}
			}
		} catch (Exception e) {
			responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		responseEntity=new ResponseEntity<>(list,HttpStatus.OK);
		return responseEntity;
	}
}
