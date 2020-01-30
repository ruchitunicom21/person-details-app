package com.mycompany.personEntities.model;

import java.io.Serializable;
import java.util.List;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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

}
