package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.commands.UnitOfMeasureCommand;
import com.am.spring.recipespringboot.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.am.spring.recipespringboot.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUnits() {
        Set<UnitOfMeasureCommand> units= new HashSet<>();
        unitOfMeasureRepository.findAll().forEach(unitOfMeasure -> units.add(converter.convert(unitOfMeasure)));
        return units;
    }
}
