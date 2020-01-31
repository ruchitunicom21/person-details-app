package com.mycompany.personEntities.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;

    @Column(name = "first_name")
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;

    @Column(name = "age")
    @JsonProperty("age")
    private int age;

    @Column(name = "favorite_colour")
    @JsonProperty("favorite_colour")
    private String favoriteColour;

    @ElementCollection
    @CollectionTable(name = "hobby")
    @JsonProperty("hobby")
    private List<String> hobby;

    @Override
    public int hashCode() {

        return firstName.hashCode()+lastName.hashCode()+age+favoriteColour.hashCode()+hobby.hashCode();
    }


    @Override
    public boolean equals(Object obj) {
        boolean isEqual=false;
      if(obj instanceof Person){
          Person person=(Person)obj;
          if(person.getFirstName().equalsIgnoreCase(this.getFirstName()) &&
                  person.getLastName().equalsIgnoreCase(this.getLastName()) &&
                  person.getAge()==this.getAge() && person.getFavoriteColour().equalsIgnoreCase(this.getFavoriteColour())){
              if(this.getHobby().size()==person.getHobby().size()){
                  for(String hobby:this.getHobby()){
                      if(person.getHobby().contains(hobby)==false){
                          break;
                      }else{isEqual=true;}

                  }

              }

          }
      }
      return isEqual;
    }
}
