package com.am.spring.recipespringboot.repositories;

import com.am.spring.recipespringboot.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>
{
    Optional<Category> findByDescription(String description);
}
