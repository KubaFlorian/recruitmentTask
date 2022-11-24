package com.recruitmenttask.repository;

import com.recruitmenttask.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentByImieAndNazwisko(String name, String lastName);

}
