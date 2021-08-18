package com.jbond.projects.spring_cource.hibernate_learn.entity.Relations.ManyToManyAlternative;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student_alt")
// Данная аннотация говорит что класс неизменяем (Иммутабелен)
@Immutable
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="surname")
    private String surname;

    @Transient // Не хранимое в бд поле.
    private int transientInt;

    @OneToMany(mappedBy = "student")
    private Set<ProfessorXStudent> professorsXstudent = new HashSet<ProfessorXStudent>();

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
