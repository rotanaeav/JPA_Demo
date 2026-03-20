package co.istad.backend.springbootmvc.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// MAKE POJO : Plain Old Java Object
@Getter
@Setter
@NoArgsConstructor
//Entity
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String name; //Default String = 255 char
    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
