package org.courses.api.course;

import org.courses.api.course.category.CourseCategoryRepository;
import org.courses.api.course.dto.CreateCourseDTO;
import org.courses.api.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    Repository repository;

    @Autowired
    CourseCategoryRepository courseCategoryRepository;

    public CourseEntity createCourse(CreateCourseDTO data) {
        // Validate category
        this.courseCategoryRepository.findById(data.category()).orElseThrow(() -> new IllegalArgumentException("Invalid category id"));


        var newCourse = new CourseEntity();
        newCourse.setCourseCategoryId(data.category());
        newCourse.setName(data.name());
        newCourse.setActive(true);

        return this.repository.save(newCourse);
    }

    public void deleteCourse(UUID courseId) {
        this.repository.findById(courseId).orElseThrow(() -> new NotFound("No course has been found with the provided ID"));
        this.repository.deleteById(courseId);
    }
}
