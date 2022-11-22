package ru.hogwarts.school.controller;

import liquibase.pro.packaged.L;
import liquibase.pro.packaged.S;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Collection<Student> findByAgeBetween(@RequestParam int min,
                                                @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);

    }

    @GetMapping("facultyOfStudent")
    public Faculty getNumberFacultyOfStudent(@RequestParam long id) {
        return studentService.getNumberFacultyOfStudent(id);
    }

    @GetMapping("/numberAllStudent")
    public int getNumberAllStudent() {
        return studentService.getByNumberAllStudent();
    }

    @GetMapping("/ageMedium")
    public double getAgeMedium() {
        return studentService.getByAgeMedium();
    }

    @GetMapping("/studentLimit")

    public List<Student> getStudentByLimit() {
        return studentService.getStudentByLimit();
    }

    @GetMapping("/namesStudentStartsWhithA")
    public Stream<String> getNamesStartWithASorted() {
        return studentService.getNamesStartWithASorted();
    }

    @GetMapping("/mediumAge")
    public double getMediumAge() {
        return studentService.getMediumAge();
    }


    @GetMapping("/print")
    public void printStudents() {
        studentService.printStudents();
    }

    @GetMapping("/printSync")
    public void printStudentsSync() {
        studentService.printStudentsSync();
    }
}


