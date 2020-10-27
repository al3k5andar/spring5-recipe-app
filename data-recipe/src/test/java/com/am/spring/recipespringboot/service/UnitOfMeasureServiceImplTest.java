package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.commands.UnitOfMeasureCommand;
import com.am.spring.recipespringboot.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.am.spring.recipespringboot.domain.UnitOfMeasure;
import com.am.spring.recipespringboot.repositories.UnitOfMeasureRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

public class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToUnitOfMeasureCommand converter= new UnitOfMeasureToUnitOfMeasureCommand();

    UnitOfMeasureServiceImpl unitOfMeasureService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        unitOfMeasureService= new UnitOfMeasureServiceImpl(unitOfMeasureRepository, converter);
    }

    @Test
    public void listAllUnits() {
//        Given
        Set<UnitOfMeasure> units= new HashSet<>();
        UnitOfMeasure unit1= new UnitOfMeasure();
        unit1.setId(1L);
        units.add(unit1);

        UnitOfMeasure unit2= new UnitOfMeasure();
        unit2.setId(2L);
        units.add(unit2);

        Mockito.when(unitOfMeasureRepository.findAll()).thenReturn(units);

//        When
        Set<UnitOfMeasureCommand> unitOfMeasures= unitOfMeasureService.listAllUnits();

//        Then
        Assert.assertEquals(2, unitOfMeasures.size());
        Mockito.verify(unitOfMeasureRepository, Mockito.times(1)).findAll();

    }
}