package com.jbond.projects.spring_cource.hibernate_learn.entity.QueryLearn;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_for_query")
@Data
@NoArgsConstructor
public class UserForQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    public UserForQuery(String username) {
        this.username = username;
    }
}
