package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Comparator;


@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty create(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        faculty.setId(null);
        return facultyRepository.save(faculty);

    }

    public Faculty read(long id) {
        logger.info("Was invoked method for read faculty");
        return facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
    }

    public Faculty update(long id, Faculty newFaculty) {
        logger.info("Was invoked method for update faculty");
        Faculty oldFaculty = read(id);
        oldFaculty.setColor(newFaculty.getColor());
        oldFaculty.setName(newFaculty.getName());
        return facultyRepository.save(oldFaculty);
    }

    public Faculty delete(long id) {
        logger.info("Was invoked method for delete faculty");
        Faculty faculty = read(id);
        facultyRepository.delete(faculty);
        return faculty;
    }

    public Collection<Faculty> findByName(String name) {
        logger.info("Was invoked method for find by name faculty");
        return facultyRepository.findByNameIgnoreCase(name);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Was invoked method for find by color faculty");
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public Collection<Student> getStudentOfFaculty(long faculty_id) {
        logger.info("Was invoked method for get student of faculty");
        return studentRepository.findStudentByFaculty_Id(faculty_id);
    }

    public String getLongestFacultyName() {
        return facultyRepository.findAll().stream()
                .map(Faculty -> Faculty.getName())
                .max(Comparator.comparingInt(String::length))
                .orElseThrow();
    }

 }





