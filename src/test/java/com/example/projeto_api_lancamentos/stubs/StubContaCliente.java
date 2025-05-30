package com.example.projeto_api_lancamentos.stubs;

import com.example.projeto_api_lancamentos.model.ContaClienteEntity;
import com.example.projeto_api_lancamentos.model.dto.TransacaoBaseDTO;
import com.example.projeto_api_lancamentos.model.dto.TransacaoRequestDTO;
import com.example.projeto_api_lancamentos.model.enums.TipoTransacaoType;

import java.util.List;

import static com.example.projeto_api_lancamentos.utils.ConstantsUnitTest.*;

public class StubContaCliente {
    public ContaClienteEntity montaEntidade() {
        return new ContaClienteEntity(ID_PADRAO, SALDO_BASE);
    }

    public TransacaoRequestDTO montaTransacoes() {
        return new TransacaoRequestDTO(List.of(enviaTransacaoCredito(), enviaTransacaoDebito()));
    }

    public TransacaoRequestDTO montaTransacoesCredito() {
        return new TransacaoRequestDTO(List.of(enviaTransacaoCredito()));
    }

    public TransacaoRequestDTO montaTransacoesDepositoValorSuperior() {
        return new TransacaoRequestDTO(List.of(enviaTransacaoDepositoSuperior()));
    }

    public TransacaoRequestDTO montaTransacoesCreditoZero() {
        return new TransacaoRequestDTO(List.of(enviaTransacaoCreditoZero()));
    }


    public TransacaoRequestDTO montaTransacoesDebito() {
        return new TransacaoRequestDTO(List.of(enviaTransacaoDebito()));
    }

    public TransacaoBaseDTO enviaTransacaoCredito() {
        return new TransacaoBaseDTO(VALOR_TRANSACAO, TipoTransacaoType.CREDITO);
    }

    public TransacaoBaseDTO enviaTransacaoDepositoSuperior() {
        return new TransacaoBaseDTO(VALOR_DEBITO_SUPERIOR, TipoTransacaoType.DEBITO);
    }

    public TransacaoBaseDTO enviaTransacaoCreditoZero() {
        return new TransacaoBaseDTO(VALOR_CREDITO_ZERO, TipoTransacaoType.CREDITO);
    }

    public TransacaoBaseDTO enviaTransacaoDebito() {
        return new TransacaoBaseDTO(VALOR_TRANSACAO_D, TipoTransacaoType.DEBITO);
    }
}
