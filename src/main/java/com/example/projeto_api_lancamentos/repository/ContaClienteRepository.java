package com.example.projeto_api_lancamentos.repository;

import com.example.projeto_api_lancamentos.model.ContaClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaClienteRepository extends JpaRepository<ContaClienteEntity, Long> {
}
