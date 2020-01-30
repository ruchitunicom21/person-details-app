package com.mycompany.personEntities.repository;

import com.mycompany.personEntities.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
