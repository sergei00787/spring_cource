package com.jbond.projects.spring_cource.hibernate_learn.entity.Constraints;

import com.jbond.projects.spring_cource.HibernateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import javax.persistence.*;

@Entity
@Table(name = "constraints_domain")
@Data
@NoArgsConstructor
public class DomainConstr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private long id;

    // Устраиваем проверку что колонка является email, в базе есть тип (domain) email
    // который мы создали, а так же что поле уникально, и не может быть пустым.
    @Column(columnDefinition = "email", unique = true, nullable = false)
    private String email;

    public DomainConstr(String email) {
        this.email = email;
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        DomainConstr domainConstr1 = new DomainConstr("asd@asd.ru");
        DomainConstr domainConstr2 = new DomainConstr("1@asd.ru");

        session.save(domainConstr1);
        session.save(domainConstr2);
        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }

}
