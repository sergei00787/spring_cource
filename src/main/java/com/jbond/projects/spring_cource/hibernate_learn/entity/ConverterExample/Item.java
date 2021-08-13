package com.jbond.projects.spring_cource.hibernate_learn.entity.ConverterExample;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "converter_item")
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @UpdateTimestamp
    @Column(name="updated")
    private Date date;

    @Type(type = "yes_no")
    @Column(name = "is_enable")
    private boolean isEnable;

    @Convert(converter = DateStringConverter.class)
    private Date strDate;

    public Item(boolean isEnable, Date strDate) {
        this.isEnable = isEnable;
        this.strDate = strDate;
    }
}
