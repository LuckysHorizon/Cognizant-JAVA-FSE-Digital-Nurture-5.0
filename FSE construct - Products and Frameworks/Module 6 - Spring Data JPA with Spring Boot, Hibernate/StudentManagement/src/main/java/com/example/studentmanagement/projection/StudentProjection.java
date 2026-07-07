package com.example.studentmanagement.projection;

import org.springframework.beans.factory.annotation.Value;

public interface StudentProjection {
    String getName();
    String getEmail();

    @Value("#{target.name + ' - ' + target.email}")
    String getStudentInfo();
}
