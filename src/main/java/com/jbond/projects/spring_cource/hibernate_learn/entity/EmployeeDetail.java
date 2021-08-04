package com.jbond.projects.spring_cource.hibernate_learn.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee_detail")
@Data
@NoArgsConstructor
public class EmployeeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "city")
    private String city;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "detail", cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Employee employee;

    public EmployeeDetail(String city, String email) {
        this.city = city;
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmployeeDetail{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
