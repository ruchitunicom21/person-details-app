package com.mycompany.personEntities.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "person")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long HobbyId;

    @ManyToOne
    @JoinColumn(name="personId")
    private Person person;
}
