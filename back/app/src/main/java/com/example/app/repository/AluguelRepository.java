package com.example.app.repository;

import com.example.app.model.Aluguel;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> 59ed827f9082e310da594185d46356a6fcbd4e65

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

    List<Aluguel> findByClienteId(Long clienteId);

    List<Aluguel> findByClienteIdOrderByDataEntradaDesc(Long clienteId);
<<<<<<< HEAD

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Aluguel a left join fetch a.pagamento where a.id = :id")
    Optional<Aluguel> findByIdForPaymentUpdate(Long id);
=======
>>>>>>> 59ed827f9082e310da594185d46356a6fcbd4e65
}
