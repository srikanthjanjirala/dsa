package com.dsa.practice.config;

import com.dsa.practice.enums.RoleName;
import com.dsa.practice.model.Course;
import com.dsa.practice.model.Role;
import com.dsa.practice.repository.CourseRepository;
import com.dsa.practice.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository, CourseRepository courseRepository) {
        return args -> {

            if (roleRepository.findByName(RoleName.ROLE_USER).isEmpty()) {
                Role userRole = new Role();
                userRole.setName(RoleName.ROLE_USER);
                roleRepository.save(userRole);
            }

            if (roleRepository.findByName(RoleName.ROLE_ADMIN).isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName(RoleName.ROLE_ADMIN);
                roleRepository.save(adminRole);
            }

            // --- Initialize Courses ---
            if (courseRepository.findByCourseName("Spring Boot").isEmpty()) {
                Course springCourse = new Course();
                springCourse.setCourseName("Spring Boot");
                courseRepository.save(springCourse);
            }

            if (courseRepository.findByCourseName("Hibernate JPA").isEmpty()) {
                Course hibernateCourse = new Course();
                hibernateCourse.setCourseName("Hibernate JPA");
                courseRepository.save(hibernateCourse);
            }

            if (courseRepository.findByCourseName("React JS").isEmpty()) {
                Course reactCourse = new Course();
                reactCourse.setCourseName("React JS");
                courseRepository.save(reactCourse);
            }

            System.out.println("Roles initialized successfully!");
        };
    }
}
