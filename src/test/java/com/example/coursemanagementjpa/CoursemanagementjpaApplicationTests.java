package com.example.coursemanagementjpa;

import com.example.coursemanagementjpa.entity.Category;
import com.example.coursemanagementjpa.entity.Course;
import com.example.coursemanagementjpa.entity.User;
import com.example.coursemanagementjpa.repository.CategoryRepository;
import com.example.coursemanagementjpa.repository.CourseRepository;
import com.example.coursemanagementjpa.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class CoursemanagementjpaApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void save_category() {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            Category category = Category.builder()
                    .name(faker.leagueOfLegends().champion())
                    .build();
            categoryRepository.save(category);
        }
    }

    @Test
    void save_user() {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().cellPhone())
                    .avatar(faker.company().logo())
                    .build();
            userRepository.save(user);
        }
    }

    @Test
    void save_course() {
        Faker faker = new Faker();
        Random rd = new Random();

        List<Category> categories = categoryRepository.findAll();
        List<User> users = userRepository.findAll();

        for (int i = 0; i < 20; i++) {
            // random 1 user
            User user = users.get(rd.nextInt(users.size()));
            // random 1 category
            List<Category> rdCategorys = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Category category = categories.get(rd.nextInt(categories.size()));
                if (!rdCategorys.contains(category)) {
                    rdCategorys.add(category);
                }
            }

            // tạo khóa học course
            Course course = Course.builder()
                    .name(faker.book().title())
                    .description(faker.lorem().sentence())
                    .type(rd.nextInt(2) == 0 ? "online" : " onLab")
                    .thumbnail(faker.company().logo())
                    .price(faker.number().numberBetween(500_000, 3_000_000))
                    .rating(faker.number().randomDouble(1, 3, 5))
                    .user(user)
                    .categories(rdCategorys)
                    .build();

            courseRepository.save(course);
        }


    }

}
