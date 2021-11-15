package com.vinntech.cinematrailer.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Genero {

    @Id
    @Column(name = "idgenero")
    private Integer Id;
    private String titulo;

}
