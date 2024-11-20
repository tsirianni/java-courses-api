package org.courses.api.course;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.courses.api.course.dto.CreateCourseDTO;
import org.courses.api.course.dto.DeleteCourseDTO;
import org.courses.api.exceptions.ErrorTypes;
import org.courses.api.exceptions.NotFound;
import org.courses.api.exceptions.dto.UnprocessableEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@Valid @PathParam("id") DeleteCourseDTO data) {
        try {
            this.service.deleteCourse(UUID.fromString(data.id()));
            return ResponseEntity.noContent().build();
        } catch (NotFound ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
