package com.jbond.projects.spring_cource.hibernate_learn.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="professor")
@Data
@NoArgsConstructor

// Динамическая вставка и изменение более оптимально при большом количестве столбцов
// или чрезмерно большое количество сущностей. Тк по умолчанию запросы всегда подготовлены для кажд хранимого класса.
@DynamicInsert
@DynamicUpdate
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name="surname")
    private String surname;

    @ManyToMany(mappedBy = "professors")
    private List<Student> students;

    public Professor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
