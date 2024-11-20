package org.courses.api.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Repository extends JpaRepository<CourseEntity, UUID> {
}
