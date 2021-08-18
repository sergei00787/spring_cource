package com.jbond.projects.spring_cource.hibernate_learn.entity.Relations.ManyToManyAlternative;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ProfessorXStudent")
@Immutable
public class ProfessorXStudent {
    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "professor_id")
        private long professorId;
        @Column(name = "student_id")
        private long studentId;

        public Id() {
        }

        public Id(long professorId, long studentId) {
            this.professorId = professorId;
            this.studentId = studentId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return professorId == id.professorId && studentId == id.studentId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(professorId, studentId);
        }
    }

    @EmbeddedId
    private Id id = new Id();

    @Column(name = "date_add")
    private Date dateAdd;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "professor_id", insertable = false, updatable = false)
    private Professor professor;

    public ProfessorXStudent(Date dateAdd, Professor professor, Student student){
        this.dateAdd = dateAdd;
        this.student = student;
        this.professor = professor;

        this.id.professorId = professor.getId();
        this.id.studentId = student.getId();

        professor.getProfessorsXstudent().add(this);
        student.getProfessorsXstudent().add(this);
    }
}
