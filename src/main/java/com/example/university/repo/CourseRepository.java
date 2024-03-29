package com.example.university.repo;

import com.example.university.domain.Staff;
import com.example.university.domain.Student;
import com.example.university.view.CourseView;
import com.example.university.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Integer>{

    Course findByName(String name);

    List<Course> findByDepartmentChairMemberLastName(String chair);

    @Query("Select c from Course c where c.department.chair.member.lastName=:chair")
    List<Course> findByChairLastName(@Param("chair")String chairLastName);

    @Query("Select c from Course c join c.prerequisites p where p.id = ?1")
    List<Course> findCourseByPrerequisite(int id);

    @Query("Select new com.example.university.view.CourseView" +
            "(c.name, c.instructor.member.lastName, c.department.name) from Course c where c.id=?1")
    CourseView getCourseView(int courseId) ;

    List<Course> findByCredits(@Param("credits") int credits);

    Page<Course> findByCredits(@Param("credits") int credits, Pageable pageable);

//      Common Querying Mistake
//      Uncomment to Debug.
//
//    Course findByDeptName(String deptName);
//
//    @Query("Select new com.example.university.view.CourseView" +
//            "(c.name, c.instructor.member.lastName, c.department.name) from course c where c.name=?1")
//    Course getCourseViewByName(String name);

}
