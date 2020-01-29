package com.mycompany.personEntities.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "person")
public class Person {

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
	
	@Column(name = "favourite_colour")
	@JsonProperty("favourite_colour")
	private String favouriteColour;

	@Column(name = "hobby")
	@OneToMany(targetEntity=Hobby.class, mappedBy="person", fetch=FetchType.EAGER)
	private List<Hobby> hobby;

}
