package com.mycompany.personEntities.controller;

import com.mycompany.personEntities.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.personEntities.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PersonEntities {

	@Autowired
    PersonServices personServices;

	@RequestMapping(value="/persons",method = RequestMethod.POST)
	public ResponseEntity<List<Long>> createPerson(@RequestBody Map<String, List <Person>> person)  {

		List<Long> list= null;
		ResponseEntity<List<Long>> responseEntity=null;
		try {
			list = personServices.save(person.get("person"));
		} catch (Exception e) {
			responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		responseEntity=new ResponseEntity<>(list,HttpStatus.OK);
		return responseEntity;
	}

}
