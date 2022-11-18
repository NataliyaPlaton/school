package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student getById(Long id);

    Collection<Student> findAllByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findStudentByFaculty_Id(long faculty_id);

    @Query(value = "select count(*) from student", nativeQuery = true)
    int getByNumberAllStudent();

    @Query(value = "select avg(age) from student", nativeQuery = true)
    double getByAgeMedium();

    @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
    List<Student> getStudentByLimit();

}