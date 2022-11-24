package com.recruitmenttask.controller;

import com.recruitmenttask.model.Nauczyciel;
import com.recruitmenttask.model.Student;
import com.recruitmenttask.repository.StudentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public Student saveStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping("/find/all")
    public List<Student> findAllStudents(@RequestParam int sort, @RequestParam int itemsPerPage, @RequestParam int pageNum) {
        if (sort == 1) {
            return repository.findAll(PageRequest.of(pageNum,itemsPerPage, Sort.Direction.DESC)).stream().toList();
        } else {
            return repository.findAll(PageRequest.of(pageNum,itemsPerPage, Sort.Direction.ASC)).stream().toList();
        }
    }

    @GetMapping("/find/id/{id}")
    public Student findStudById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/find/name/{name}/{lastName}")
    public List<Student> findByNameAndLastName(@PathVariable String name, @PathVariable String lastName) {
        return repository.findStudentByImieAndNazwisko(name, lastName);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping("/teachers/{id}")
    public List<Nauczyciel> getStudentTeacher(@PathVariable Long id) {
        return repository.findById(id).get().getNauczyciele().stream().toList();
    }
}
