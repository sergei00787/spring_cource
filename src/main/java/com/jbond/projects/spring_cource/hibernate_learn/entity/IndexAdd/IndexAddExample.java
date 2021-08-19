package com.jbond.projects.spring_cource.hibernate_learn.entity.IndexAdd;

import com.jbond.projects.spring_cource.HibernateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.Date;

@Entity
// Добавляем индексы по 2м полям
@Table(name = "index_add_example",
        indexes = {
                @Index(name = "date_start_end_indx", columnList = "start_date, end_date"),
                @Index(name = "username_indx", columnList = "user_name")
        }
)

@Data
@NoArgsConstructor
public class IndexAddExample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private long id;

    @Column(name = "user_name")
    private String username;

    @Column(columnDefinition = "email", nullable = false)
    private String email;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    public IndexAddExample(String username, String email, Date startDate, Date endDate) {
        this.username = username;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        IndexAddExample indexAddExample1 = new IndexAddExample("vasya2", "asd2@asd.ru", Date.valueOf("2021-01-01"), Date.valueOf("2021-02-02"));
        IndexAddExample indexAddExample2 = new IndexAddExample("petya3", "petruha3@asd.ru", Date.valueOf("2021-02-01"), Date.valueOf("2021-01-02"));

        session.save(indexAddExample1);
        session.save(indexAddExample2);
        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }
}
