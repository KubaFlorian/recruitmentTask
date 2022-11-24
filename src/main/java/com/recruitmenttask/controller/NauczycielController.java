package com.recruitmenttask.controller;

import com.recruitmenttask.model.Nauczyciel;
import com.recruitmenttask.model.Student;
import com.recruitmenttask.repository.NauczycielRepository;
import com.recruitmenttask.service.SortService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class NauczycielController {

    private NauczycielRepository repository;
    private SortService service = new SortService();

    public NauczycielController(NauczycielRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public Nauczyciel saveTeacher(@RequestBody Nauczyciel teacher) {
        return repository.save(teacher);
    }

    @GetMapping("/find/all")
    public List<Nauczyciel> findAllTeachers(@RequestParam int sort, @RequestParam int itemsPerPage, @RequestParam int pageNum) {
        if (sort == 1) {
            return repository.findAll(PageRequest.of(pageNum,itemsPerPage, Sort.Direction.DESC)).stream().toList();
        } else {
            return repository.findAll(PageRequest.of(pageNum,itemsPerPage, Sort.Direction.ASC)).stream().toList();
        }
    }

    @GetMapping("/find/id/{id}")
    public Nauczyciel findTeacherById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/find/name/{name}/{lastName}")
    public List<Nauczyciel> findByNameAndLastName(@PathVariable String name, @PathVariable String lastName) {
        return repository.findNauczycielByImieAndNazwisko(name, lastName);
    }

    @PutMapping("/update")
    public Nauczyciel updateTeacher(@RequestBody Nauczyciel teacher) {
        return repository.save(teacher);
    }

    @GetMapping("/teachers/{id}")
    public List<Student> getStudentTeacher(@PathVariable Long id) {
        return repository.findById(id).get().getStudenci().stream().toList();
    }
}
