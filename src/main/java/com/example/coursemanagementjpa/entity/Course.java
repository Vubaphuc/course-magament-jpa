package com.example.coursemanagementjpa.entity;

import com.example.coursemanagementjpa.dto.CourseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "listCourse", // tên kết quả ở bước 1
                classes = @ConstructorResult(
                        targetClass = CourseDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "type", type = String.class),
                                @ColumnResult(name = "thumbnail", type = String.class),
                                @ColumnResult(name = "price", type = Integer.class),
                                @ColumnResult(name = "rating", type = Double.class),
                                @ColumnResult(name = "user", type = String.class)
                        }
                )

        )
})
@NamedNativeQuery(
        name = "findUserDemo", // Đặt tên cho câu lệnh query sử dụng trong repo
        resultSetMapping = "listCourse", // Đặt tên cho kết quả trả về -> sử dụng ở step 2
        query = "select c.*, \n" +
                "JSON_OBJECT(\"id\", u.id, \"name\", u.name, \"email\", u.email, \"phone\", u.phone, \"avatar\", u.avatar) as user \n" +
                "from course c\n" +
                "left join course_category cc \n" +
                "on c.id = cc.course_id\n" +
                "left join category ct\n" +
                "on cc.category_id = ct.id\n" +
                "left join user u\n" +
                "on c.user_id = u.id\n" +
                "where (:name is null or c.name = :name)\n" +
                "and (:type is null or c.type = :type)\n" +
                "and (:category is null or ct.name = :category)\n" +
                "group by c.id") // Định nghĩa câu lệnh native query

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private String type;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "price")
    private Integer price;
    @Column(name = "rating")
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore // Nếu không muốn trả về thông tin cho client
    @ManyToMany
    @JoinTable(name = "course_categories",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Category> categories = new ArrayList<>();

}