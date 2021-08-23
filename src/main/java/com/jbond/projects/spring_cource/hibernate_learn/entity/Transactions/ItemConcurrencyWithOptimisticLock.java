package com.jbond.projects.spring_cource.hibernate_learn.entity.Transactions;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "item_with_optimistic_lock")
//@OptimisticLocking(type = OptimisticLockType.ALL)
// Когда не возможно создать столбец версии или отметки времени
// Альтернативная реализация версионирования сравнивает текущее состояние в базе данных
// с неизмененными значениями хранимых свойств на момент загрузки экземпляра сущности
//@DynamicUpdate
//Активируя динамическое создание инструкций вставки и изменения, вы сообщаете Hibernate,
// что строки SQL должны формироваться по требованию, а не заранее.
// В этом случае инструкция UPDATE будет содержать только столбцы с новыми значениями,
// а INSERT – только столбцы, которые не могут принимать значение null.
@Data
@NoArgsConstructor
public class ItemConcurrencyWithOptimisticLock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //@Version // Простой счетчик изменений, который автоматически увеличивается при окончании транзакции на изменение
    // таким образом побеждает первая закомиченная транзакция
    //private Long version;

    @Column(name = "number_dont_care")
    @OptimisticLock(excluded = true) // Обозначает что изменение этого поля не будет увеличивать счетчик версии
    private int number_dont_care;

    @Version  // Счетчик который записывает время обновления
    @Type(type = "dbtimestamp") // Необязательно, В этом случае Hibernate будет запрашивать текущее время
    // у базы данных перед обновлением, тк может быть рассинхронизация на разных серверах
    // Не все SQL-диалекты Hibernate поддерживают это, поэтому загляните в исходный код своего диалекта, чтобы узнать, переопределяет ли
    // он метод getCurrentTimestampSelectString().
    private Date lastUpdate;


}
