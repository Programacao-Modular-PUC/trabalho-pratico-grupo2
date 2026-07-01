package com.example.app.repository;

import com.example.app.model.PacoteHospedagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacoteHospedagemRepository extends JpaRepository<PacoteHospedagem, Long> {
    List<PacoteHospedagem> findByAtivoTrue();
    boolean existsByNome(String nome);
}
