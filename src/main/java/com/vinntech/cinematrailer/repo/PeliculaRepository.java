package com.vinntech.cinematrailer.repo;

import com.vinntech.cinematrailer.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
}
