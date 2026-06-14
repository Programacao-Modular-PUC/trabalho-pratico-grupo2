package com.example.app.repository;

import com.example.app.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    @Query("SELECT q FROM Quarto q WHERE TYPE(q) = QuartoIndividual")
    List<Quarto> findAllIndividuais();

    @Query("SELECT q FROM Quarto q WHERE TYPE(q) = QuartoDuplo")
    List<Quarto> findAllDuplos();

    List<Quarto> findByDisponivelTrue();
}
