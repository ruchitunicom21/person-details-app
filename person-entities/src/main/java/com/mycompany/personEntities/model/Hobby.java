package com.mycompany.personEntities.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "person")
public class Hobby implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hobbyId;

    private String hobbyName;

    @ManyToMany(mappedBy = "hobby")
    private List<Person> person=new ArrayList<>();
}
