package co.istad.backend.springbootmvc.repository;

import co.istad.backend.springbootmvc.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
extends JpaRepository<Category, Integer> {
}
