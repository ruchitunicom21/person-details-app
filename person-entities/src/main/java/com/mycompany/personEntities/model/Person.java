package com.mycompany.personEntities.model;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Person implements Serializable{

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


	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "person_hobby",
			joinColumns = @JoinColumn(name = "person_id"),
			inverseJoinColumns = @JoinColumn(name = "hobby_id")
	)
	private List<Hobby> hobby=new ArrayList<>();

}
