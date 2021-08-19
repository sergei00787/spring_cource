package com.jbond.projects.spring_cource.hibernate_learn.entity.Constraints;

import com.jbond.projects.spring_cource.HibernateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.sql.Date;

@Entity
// Строим уникальную проверку по 2ум столбцам
@Table(name = "constraints_table",
        uniqueConstraints = @UniqueConstraint(
                name = "unq_username_email",
                columnNames = {"user_name", "email"}))
// Строим проверку что время старта должно быть меньше время окончания
@Check(constraints = "start_date < end_date")
@Data
@NoArgsConstructor
public class TableConstr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private long id;

    @Column(name = "user_name")
    private String username;

    @Column(columnDefinition = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    public TableConstr(String username, String email, Date startDate, Date endDate) {
        this.username = username;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        TableConstr tableConstr1 = new TableConstr("vasya2", "asd2@asd.ru", Date.valueOf("2021-01-01"), Date.valueOf("2021-02-02"));
        TableConstr tableConstr2 = new TableConstr("petya3", "petruha3@asd.ru", Date.valueOf("2021-02-01"), Date.valueOf("2021-01-02"));

        session.save(tableConstr1);
        session.save(tableConstr2);
        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }
}
