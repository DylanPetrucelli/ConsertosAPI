package br.edu.ifsp.prw3.av3.conserto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsertoRepository extends JpaRepository<Conserto, Long> {
    Page<Conserto> findAllByAtivoTrue(Pageable paginacao);
}
