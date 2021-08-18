package com.jbond.projects.spring_cource.hibernate_learn.entity.Relations;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(generator = "emp_details_generator")
    @GenericGenerator(name = "emp_details_generator",
            strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "detail"))
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "salary")
    private int salary;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    // @PrimaryKeyJoinColumn - Применяется при связи сущьности с общим первичным ключем (так делают редко)
    // @JoinColumn(unique = true) - Применяется при связи с отдельным вторичным ключем
    // @JoinTable - Применяется при создании связи через промежуточную таблицу содержащую первичные ключи обеих сущностей
    // Пример-
//    @JoinTable(
//            name = "ITEM_SHIPMENT", - имя здесь обязательно!
//            joinColumns = @JoinColumn(name = "SHIPMENT_ID"),
//            inverseJoinColumns = @JoinColumn(name = "ITEM_ID", nullable = false, unique = true)
//    )
    private EmployeeDetail detail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    public Employee(String name, String surname, int salary, EmployeeDetail detail) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.detail = detail;
    }

    public Employee(EmployeeDetail detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                '}';
    }
}
