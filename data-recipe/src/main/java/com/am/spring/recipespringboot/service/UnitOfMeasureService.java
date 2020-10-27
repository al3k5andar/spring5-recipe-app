package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService
{
    Set<UnitOfMeasureCommand> listAllUnits();
}
