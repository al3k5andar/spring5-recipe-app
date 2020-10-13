package com.am.spring.recipespringboot.repositories;

import com.am.spring.recipespringboot.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>
{
    Optional<UnitOfMeasure> findByDescription(String description);
}
