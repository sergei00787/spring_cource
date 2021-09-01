package com.jbond.projects.spring_cource.hibernate_learn.entity.QueryLearn;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Table(name = "user_for_query_with_collection")
@Data
@NoArgsConstructor
public class UserForQueryWithCollection {
        private String username;

    public UserForQueryWithCollection(String username) {
        this.username = username;
    }
}
