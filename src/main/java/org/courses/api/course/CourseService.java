package org.courses.api.course;

import org.courses.api.course.category.CourseCategoryRepository;
import org.courses.api.course.dto.CreateCourseDTO;
import org.courses.api.course.dto.FindAllCoursesDTO;
import org.courses.api.course.dto.ToggleCourseStateDTO;
import org.courses.api.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    Repository repository;

    @Autowired
    CourseCategoryRepository courseCategoryRepository;

    public CourseEntity createCourse(CreateCourseDTO data) {
        // Validate category
        var category = this.courseCategoryRepository.findById(data.category()).orElseThrow(() -> new IllegalArgumentException("Invalid category id"));


        var newCourse = new CourseEntity();
        newCourse.setCourseCategory(category);
        newCourse.setName(data.name());
        newCourse.setActive(true);

        return this.repository.save(newCourse);
    }

    public void deleteCourse(UUID courseId) {
        this.repository.findById(courseId).orElseThrow(() -> new NotFound("No course has been found with the provided ID"));
        this.repository.deleteById(courseId);
    }

    public CourseEntity toggleCourseState(ToggleCourseStateDTO data) {
        var course = this.repository.findById(UUID.fromString(data.id())).orElseThrow(
                () -> new NotFound("No course has been found with the provided ID"));


        course.setActive(!course.isActive());
        return this.repository.save(course);
    }

    public List<CourseEntity> findAllCourses(FindAllCoursesDTO filters) {
        boolean shouldFilterByCourseCategory = filters.courseCategoryId().isPresent();
        boolean shouldFilterByName = filters.name().isPresent();

        if (shouldFilterByName || shouldFilterByCourseCategory) {
            var name = filters.name().orElse(null);
            var courseCategoryId = filters.courseCategoryId().orElse(null);

            return this.repository.findAllWithFilters(name, courseCategoryId);
        }

        return this.repository.findAll();
    }
}
