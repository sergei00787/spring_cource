package com.jbond.projects.spring_cource.hibernate_learn.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="student_professor",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "professor_id")})
    private List<Professor> professors;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
