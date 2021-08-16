package com.jbond.projects.spring_cource.hibernate_learn.entity.Collections.SetComponents;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class Component {
    private String name;
    private int width;
    private int height;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return width == component.width && height == component.height && Objects.equals(name, component.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, width, height);
    }

    public Component(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }
}
