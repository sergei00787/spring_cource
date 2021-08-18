package com.jbond.projects.spring_cource.hibernate_learn.entity.Relations.ManyToManyAlternative;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professor_alt")
@Data
@NoArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "professor")
    private Set<ProfessorXStudent> professorsXstudent = new HashSet<ProfessorXStudent>();

    public Professor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
