package com.example.university.repo;

import com.example.university.domain.Person;
import com.example.university.domain.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer>{

    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject

    //Simple Query Methods
    List<Student> findByFullTime(boolean fullTime);
    List<Student> findByAge(Integer age);
    List<Student> findByAttendeeLastName(String last);

    //Query Methods with Clauses and Expressions
    Student findByAttendeeFirstNameAndAttendeeLastName(String firstName, String lastName);
    Student findByAttendee(Person person);
    List<Student> findByAgeGreaterThan(int minimumAge);
    List<Student> findByAgeLessThan(int maximumAge);
    List<Student> findByAttendeeLastNameIgnoreCase(String lastName);
    List<Student> findByAttendeeLastNameLike(String likeString);
    //Find
    Student findFirstByOrderByAttendeeLastNameAsc();
    //Find the oldest student
    Student findTopByOrderByAgeDesc();
    List<Student> findTop3ByOrderByAgeDesc();
}
