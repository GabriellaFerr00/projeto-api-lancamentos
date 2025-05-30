package com.example.projeto_api_lancamentos.service;

import com.example.projeto_api_lancamentos.handler.Messages;
import com.example.projeto_api_lancamentos.handler.exception.RegrasBuscaRepositoryException;
import com.example.projeto_api_lancamentos.handler.exception.RegrasDadosDeEntradaException;
import com.example.projeto_api_lancamentos.model.ContaClienteEntity;
import com.example.projeto_api_lancamentos.model.dto.SaldoResponseDTO;
import com.example.projeto_api_lancamentos.repository.ContaClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContaClienteService {

    private final ContaClienteRepository repository;

    public SaldoResponseDTO buscarSaldoConta(Long idConta) {
        if(idConta == null){
            throw new RegrasDadosDeEntradaException(Messages.ID_INCORRETO);
        }

        ContaClienteEntity conta = repository.findById(idConta)
                .orElseThrow(() -> new RegrasBuscaRepositoryException(Messages.CONTA_NAO_ENCONTRADA + idConta));

        return new SaldoResponseDTO(conta.getId(), LocalDateTime.now(), conta.getSaldo());
    }
}
