package com.example.app.repository;

import com.example.app.model.ServicoAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoAdicionalRepository extends JpaRepository<ServicoAdicional, Long> {
    List<ServicoAdicional> findByAtivoTrue();
    boolean existsByNome(String nome);
}
