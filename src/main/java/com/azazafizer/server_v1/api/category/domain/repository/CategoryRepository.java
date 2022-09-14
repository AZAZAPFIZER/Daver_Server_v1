package com.azazafizer.server_v1.api.category.domain.repository;

import com.azazafizer.server_v1.api.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);
}
