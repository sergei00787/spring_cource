package com.jbond.projects.spring_cource.hibernate_learn.entity.EmbeddedEntityExample;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


// Пример встраеваемого класса EmbededCl
// и пример переопределения полей встраиваемого класса
@Entity
@Table(name = "embed_example_1")
@Data
@NoArgsConstructor
public class MainCl {

    private long id;
    private String name;

    // Встраиваемый класс
    private EmbedCl embedCl;

    public MainCl(String name, EmbedCl embedCl) {
        this.name = name;
        this.embedCl = embedCl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmbedCl getEmbedCl() {
        return embedCl;
    }

    public void setEmbedCl(EmbedCl embedCl) {
        this.embedCl = embedCl;
    }
}
