package org.courses.api.course;

import jakarta.validation.Valid;
import org.courses.api.course.dto.CreateCourseDTO;
import org.courses.api.exceptions.ErrorTypes;
import org.courses.api.exceptions.dto.UnprocessableEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class Controller {

    @Autowired
    CourseService service;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCourseDTO body) {
        try {
            var course = this.service.createCourse(body);
            return ResponseEntity.ok().body(course);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.unprocessableEntity().body(
                    new UnprocessableEntityDTO(e.getMessage(), ErrorTypes.INVALID_CATEGORY.getErrorMessage()));
        }
    }
}
