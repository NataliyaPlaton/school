package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

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
}



