package com.mycompany.personEntities.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.personEntities.model.Person;

import java.util.ArrayList;
import java.util.List;
@RestController
public class PersonEntities {
	
	
	public ResponseEntity<List<Integer>> createPerson(@RequestBody List<Person> persons){
		
		List<Integer> list=new ArrayList<>();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
