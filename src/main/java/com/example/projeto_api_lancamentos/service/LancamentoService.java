package com.example.projeto_api_lancamentos.service;

import com.example.projeto_api_lancamentos.handler.Messages;
import com.example.projeto_api_lancamentos.handler.exception.RegrasBuscaRepositoryException;
import com.example.projeto_api_lancamentos.handler.exception.RegrasDadosDeEntradaException;
import com.example.projeto_api_lancamentos.model.LancamentoEntity;
import com.example.projeto_api_lancamentos.model.dto.LancamentoResponseDTO;
import com.example.projeto_api_lancamentos.repository.LancamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LancamentoService {
    private final LancamentoRepository repository;


    public List<LancamentoResponseDTO> buscarLancamentoPorCliente(Long idConta) {
        if (idConta == null) {
            throw new RegrasDadosDeEntradaException(Messages.ID_INCORRETO);
        }

        List<LancamentoEntity> lancamentos = repository.findByContaClienteId(idConta);

        if (lancamentos.isEmpty()) {
            throw new RegrasBuscaRepositoryException(Messages.CONTA_NAO_ENCONTRADA + idConta);
        }

        return lancamentos.stream()
                .map(this::converteEntidadeParaDTO)
                .toList();
    }

    public LancamentoResponseDTO converteEntidadeParaDTO(LancamentoEntity entidade) {
        return new LancamentoResponseDTO(entidade.getTipoTransacaoType(), entidade.getValorTransacao(), entidade.getDataTransacao());
    }

}
