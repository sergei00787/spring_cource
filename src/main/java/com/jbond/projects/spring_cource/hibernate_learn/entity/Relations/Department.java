package com.jbond.projects.spring_cource.hibernate_learn.entity.Relations;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table
@Data
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String departmentName;
    @Column(name = "min_salary")
    private int minSalary;
    @Column(name = "max_salary")
    private int maxSalary;

    @OneToMany(mappedBy = "department")
    private Collection<Employee> employees = new ArrayList<>();
    // Используйте по возможности Collection, тк контейнер емеет лучшие показатели производительности
    // Поскольку контейнер не должен хранить индексов своих элементов (как
    // список) или проверять дублирования элементов (как множество), вы можете добавлять
    // в контейнер новые элементы, не инициируя при этом загрузку данных.

    // List - Если вам потребуется настоящий список, чтобы сохранить позиции элементов
    // в коллекции, вам понадобится дополнительный столбец для хранения индексов.
    // В действующем приложении вы бы не стали отображать ассоциацию, используя коллекцию List. Cопровождение
    // индексов при удалении, добавлении или сдвиге элементов списка может быть дорогостоящим
    // и вызывать выполнение множества выражений SQL

    public Department(String departmentName, int minSalary, int maxSalary) {
        this.departmentName = departmentName;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }
}
