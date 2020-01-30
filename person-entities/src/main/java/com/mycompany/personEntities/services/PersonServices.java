package com.mycompany.personEntities.services;

import com.mycompany.personEntities.model.Person;
import com.mycompany.personEntities.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServices {
    @Autowired
    private PersonRepository personRepository;


    @Transactional(rollbackOn = Exception.class)
    public List <Long> save(List<Person> personList) throws Exception{
        List <Long> list=new ArrayList<>();
        for (Person person:personList){
            Person tempPerson=personRepository.save(person);
            list.add(tempPerson.getPersonId());
        }
    return list;
    }
}
