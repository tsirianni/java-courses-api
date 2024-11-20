package org.courses.api.course.category;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCategoryEntity {
    private short id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
