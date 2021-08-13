package com.jbond.projects.spring_cource.hibernate_learn.entity.InharitanceExample.InheritanceTypeSingleTable;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "animal_single")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_animal")
//Если не определить столбец селектора в суперклассе, по умолчанию он будет
//называться DTYPE, а его значениями будут строки.

//@DiscriminatorFormula - применяется когда нет возможности добавить еще один столбец селектора в таблицы сущностей.
// например @DiscriminatorFormula("case when CARDNUMBER is not null then ‘CC’ else ‘BA’ end")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String phrase = "I am animal";

    public void say() {
        System.out.println(this.phrase);
    }

}
