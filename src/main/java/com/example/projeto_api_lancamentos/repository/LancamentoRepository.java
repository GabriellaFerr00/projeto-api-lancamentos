package com.example.projeto_api_lancamentos.repository;

import com.example.projeto_api_lancamentos.model.LancamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<LancamentoEntity, Long> {
    List<LancamentoEntity> findByContaClienteId(Long idConta);
}
