package com.jbond.projects.spring_cource.hibernate_learn.entity.QueryLearn;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item_for_query")

@NamedQueries({
        @NamedQuery(name = "findItemByName",
                query = "select i from ItemForQuery i where i.name = :paramName"
        ),
        @NamedQuery(name = "findItemBetweenDate",
        query = "select i from ItemForQuery i where i.startDate between :paramStart and :paramEnd")
})

@Data
@NoArgsConstructor
public class ItemForQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int number;
    private Date startDate;
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "iser_id")
    private UserForQuery user;

    public ItemForQuery(String name, int number, Date startDate, Date endDate, UserForQuery user) {
        this.name = name;
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ItemForQuery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + user +
                '}';
    }
}
