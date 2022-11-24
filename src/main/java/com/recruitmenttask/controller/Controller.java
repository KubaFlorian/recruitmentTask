package com.recruitmenttask.controller;

import com.recruitmenttask.model.Nauczyciel;
import com.recruitmenttask.model.Student;
import com.recruitmenttask.repository.NauczycielRepository;
import com.recruitmenttask.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    private StudentRepository studentRepository;
    private NauczycielRepository nauczycielRepository;

    public Controller(StudentRepository studentRepository, NauczycielRepository nauczycielRepository) {
        this.studentRepository = studentRepository;
        this.nauczycielRepository = nauczycielRepository;
    }

    @PostMapping("/addTeacherToStudent/{studentId}/{teacherId}")
    public void addTeacherToStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        studentRepository.findById(studentId).get().getNauczyciele()
                .add(nauczycielRepository.findById(teacherId).get());
        nauczycielRepository.findById(teacherId).get().getStudenci()
                .add(studentRepository.findById(studentId).get());
    }

    @PostMapping("/removeTeacherFromStudent/{studentId}/{teacherId}")
    public void removeTeacherFromStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        studentRepository.findById(studentId).get().getNauczyciele()
                .removeIf(obj  -> obj.getId() == teacherId);
        nauczycielRepository.findById(teacherId).get().getStudenci()
                .removeIf(obj -> obj.getId() == studentId);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        for (Nauczyciel teacher : nauczycielRepository.findAll()) {
            teacher.getStudenci().removeIf(obj -> obj.getId() == id);
        }
    }

    @DeleteMapping("/deleteTeacher/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        nauczycielRepository.deleteById(id);
        for (Student student : studentRepository.findAll()) {
            student.getNauczyciele().removeIf(obj -> obj.getId() == id);
        }
    }

}