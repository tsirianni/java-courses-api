package org.courses.api.course;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {
    private UUID id;
    private String name;
    private short courseCategoryId;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
