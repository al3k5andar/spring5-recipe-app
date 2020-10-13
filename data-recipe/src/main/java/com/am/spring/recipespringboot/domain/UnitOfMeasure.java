package com.am.spring.recipespringboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
}
