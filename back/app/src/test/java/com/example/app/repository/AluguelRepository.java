package com.example.app.repository;

import com.example.app.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {

}