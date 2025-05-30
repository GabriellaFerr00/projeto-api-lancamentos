package com.example.projeto_api_lancamentos.service;

import com.example.projeto_api_lancamentos.handler.Messages;
import com.example.projeto_api_lancamentos.handler.exception.RegrasBuscaRepositoryException;
import com.example.projeto_api_lancamentos.handler.exception.RegrasCasoDeUsoException;
import com.example.projeto_api_lancamentos.handler.exception.RegrasDadosDeEntradaException;
import com.example.projeto_api_lancamentos.model.ContaClienteEntity;
import com.example.projeto_api_lancamentos.model.LancamentoEntity;
import com.example.projeto_api_lancamentos.model.dto.SaldoResponseDTO;
import com.example.projeto_api_lancamentos.model.dto.TransacaoBaseDTO;
import com.example.projeto_api_lancamentos.model.dto.TransacaoRequestDTO;
import com.example.projeto_api_lancamentos.model.dto.TransacaoResponseDTO;
import com.example.projeto_api_lancamentos.model.enums.TipoTransacaoType;
import com.example.projeto_api_lancamentos.repository.ContaClienteRepository;
import com.example.projeto_api_lancamentos.repository.LancamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContaClienteService {

    private final ContaClienteRepository repository;

    private final LancamentoRepository lancamentoRepository;

    public SaldoResponseDTO buscarSaldoConta(Long idConta) {
        if (idConta == null) {
            throw new RegrasDadosDeEntradaException(Messages.ID_INCORRETO);
        }

        ContaClienteEntity conta = this.getContaCliente(idConta);

        return new SaldoResponseDTO(conta.getId(), LocalDateTime.now(), conta.getSaldo());
    }

    public TransacaoResponseDTO processarTransacao(Long idConta, TransacaoRequestDTO dto) {
        if (idConta == null || dto == null || dto.transacoesDiversas().isEmpty()) {
            throw new RegrasDadosDeEntradaException(Messages.CAMPOS_INCORRETOS);
        }

        ContaClienteEntity conta = this.getContaCliente(idConta);

        for (TransacaoBaseDTO entrada : dto.transacoesDiversas()) {
            if (entrada.tipoTransacao().equals(TipoTransacaoType.CREDITO)) {
                conta.transacaoDeposito(entrada.valorTransacao());
            } else if (entrada.tipoTransacao().equals(TipoTransacaoType.DEBITO)) {
                Boolean ok = conta.transacaoRetirada(entrada.valorTransacao());
                if (!ok) {
                    throw new RegrasCasoDeUsoException(Messages.SALDO_INSUFICIENTE + conta.getSaldo());
                }
            }

            LancamentoEntity lancamento = LancamentoEntity
                    .builder()
                    .tipoTransacaoType(entrada.tipoTransacao())
                    .valorTransacao(entrada.valorTransacao())
                    .contaCliente(conta)
                    .dataTransacao(LocalDateTime.now())
                    .build();

            lancamentoRepository.save(lancamento);

        }

        repository.save(conta);

        return new TransacaoResponseDTO(conta.getSaldo(), LocalDateTime.now());

    }

    public ContaClienteEntity getContaCliente(Long idConta) {
        return repository.findById(idConta)
                .orElseThrow(() -> new RegrasBuscaRepositoryException(Messages.CONTA_NAO_ENCONTRADA + idConta));
    }
}
