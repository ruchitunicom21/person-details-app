package com.mycompany.personEntities.util;


import com.mycompany.personEntities.model.Person;
import com.mycompany.personEntities.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class PersonUtil {


    private static HashMap<Person,Long> hashMap=null;

    @Autowired
    private PersonRepository personRepository;

    public HashMap<Person,Long> getCache(){
        if(hashMap==null || hashMap.size()==0){
            List<Person> personList= (List<Person>) personRepository.findAll();
            hashMap=new HashMap<>();
            for(Person person:personList){
                hashMap.put(person,person.getPersonId());
            }

        }
        return hashMap;
    }

    public boolean isExistingObject(Person person){
        if(hashMap==null ){
            hashMap=getCache();
        }
        boolean isExisting=false;
        for(Map.Entry<Person,Long> entry:hashMap.entrySet()){
              if(entry.getKey().equals(person)){
                  isExisting=true;
                  break;
              }
        }
        return isExisting;
    }

}
