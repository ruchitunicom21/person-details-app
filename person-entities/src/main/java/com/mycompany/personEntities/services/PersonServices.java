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
import java.util.Map;

@Service
public class PersonServices {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    PersonUtil personUtil;


    @Transactional(rollbackOn = Exception.class)
    public List <Person> save(List<Person> personList) throws Exception{
        List <Person> list=new ArrayList<>();
        HashMap<Long,Person> hashMap= personUtil.getCache();
        for (Person person:personList){
            if(personUtil.isExistingObject(person)==false) {
                Person tempPerson = personRepository.save(person);
                list.add(tempPerson);
                hashMap.put(tempPerson.getPersonId(),tempPerson);
            }

        }
    return list;
    }

    @Transactional(rollbackOn = Exception.class)
    public String delete(Long personId) throws Exception{
        String message="";
        HashMap<Long,Person> hashMap= personUtil.getCache();

            if(hashMap.get(personId)!=null) {
                personRepository.deleteById(personId);

                hashMap.remove(personId);
                message=personId+" has been deleted successfuly";
            }else {
                message=personId+" does not exits";
            }

        return message;
    }

    @Transactional()
    public List<Person> getAllPerson() throws Exception{
        String message="";
        List<Person> list=new ArrayList<>();
        HashMap<Long,Person> hashMap= personUtil.getCache();
        for(Map.Entry<Long,Person> entry:hashMap.entrySet()){
            list.add(entry.getValue());
        }

        return list;
    }

    @Transactional(rollbackOn = Exception.class)
    public String update(Person person,Long personId) throws Exception{
        String message="";
        HashMap<Long,Person> hashMap= personUtil.getCache();
         Person chachedPerson=hashMap.get(personId);
        if(chachedPerson!=null) {
          if(person.getFirstName()!=null){
              chachedPerson.setFirstName(person.getFirstName());
          }
            if(person.getLastName()!=null){
                chachedPerson.setLastName(person.getLastName());
            }
            if(person.getFavoriteColour()!=null){
                chachedPerson.setFavoriteColour(person.getFavoriteColour());
            }
            if(person.getAge()!=0){
                chachedPerson.setAge(person.getAge());
            }
            if(person.getHobby()!=null && person.getHobby().size()>0){
                chachedPerson.setHobby(person.getHobby());
            }
            personRepository.save(chachedPerson);
            hashMap.put(personId,chachedPerson);
            message=personId+" has been updated successfuly";
        }else {
            message=personId+" does not exits";
        }

        return message;
    }

}
