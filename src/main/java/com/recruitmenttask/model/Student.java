package com.recruitmenttask.model;

import lombok.*;
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
public class Student {

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
    private String kierunek;

    @ManyToMany(mappedBy = "studenci", fetch = FetchType.LAZY)
    private Set<Nauczyciel> nauczyciele;

}
