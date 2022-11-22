package ru.hogwarts.school.service;

import liquibase.pro.packaged.S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Was invoked method for add student");
        student.setId(null);
        logger.debug("Add " + student);
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for find student");
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        logger.debug("Delete student with id = " + id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method for find by age student");
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        if (min < 0 || max < 0 || max < min) {
            logger.error("There is illegal age: min = " + min + ", max = " + max);
            throw new IllegalArgumentException();
        }
        logger.info("Was invoked method for find by age between minimum and maximum");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getNumberFacultyOfStudent(long student_id) {
        logger.info("Was invoked method for get number faculty of student");
        return studentRepository.findById(student_id).get().getFaculty();
    }

    public int getByNumberAllStudent() {
        logger.info("Was invoked method for get by number all student");
        return studentRepository.getByNumberAllStudent();
    }

    public double getByAgeMedium() {
        logger.info("Was invoked method for get by age medium");
        return studentRepository.getByAgeMedium();
    }

    public List<Student> getStudentByLimit() {
        logger.info("Was invoked method for get student by limit");
        return studentRepository.getStudentByLimit();
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Stream<String> getNamesStartWithASorted() {
        return studentRepository.findAll().stream()
                .map(Student -> Student.getName())
                .map(name -> name.toUpperCase())
                .filter(name -> name.startsWith("A"))
                .sorted();
    }

    public double getMediumAge() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student -> Student.getAge())
                .average().orElseThrow();
    }

    public void printStudents() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printStudents(students.subList(0, 2));
        new Thread(() -> printStudents(students.subList(2, 4))).start();
        new Thread(() -> printStudents(students.subList(4, 6))).start();
    }

    private void printStudents(List<Student> students) {
        for (Student student : students) {
            logger.info(String.valueOf(student));
        }
    }

    private synchronized void printStudentsSync(List<Student> students) {
        for (Student student : students) {
            logger.info(String.valueOf(student));
        }
    }

    public void printStudentsSync() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printStudentsSync(students.subList(0, 2));
        new Thread(() -> printStudentsSync(students.subList(2, 4))).start();
        new Thread(() -> printStudentsSync(students.subList(4, 6))).start();
    }

}





