package com.mycompany.personEntities.services;

import com.mycompany.personEntities.model.Person;
import com.mycompany.personEntities.repository.PersonRepository;
import com.mycompany.personEntities.util.PersonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PersonServices {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    PersonUtil personUtil;


    @Transactional(rollbackOn = Exception.class)
    public List <Long> save(List<Person> personList) throws Exception{
        List <Long> list=new ArrayList<>();
        HashMap<Person,Long> hashMap= personUtil.getCache();
        for (Person person:personList){
            if(personUtil.isExistingObject(person)==false) {
                Person tempPerson = personRepository.save(person);
                list.add(tempPerson.getPersonId());
                hashMap.put(tempPerson,tempPerson.getPersonId());
            }

        }
    return list;
    }
}
