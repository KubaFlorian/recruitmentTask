package com.recruitmenttask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nauczyciel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(min = 3)
    private String imie;
    @Length(min = 3)
    private String nazwisko;
    @Min(25)
    private int wiek;
    @Email
    private String email;
    private String przedmiot;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "stud_naucz",
            joinColumns = {@JoinColumn(name = "nauczyciel_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")}
    )
    private Set<Student> studenci;

}
