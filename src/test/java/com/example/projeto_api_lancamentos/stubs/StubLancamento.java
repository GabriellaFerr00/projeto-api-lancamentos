package com.example.projeto_api_lancamentos.stubs;

import com.example.projeto_api_lancamentos.model.LancamentoEntity;
import com.example.projeto_api_lancamentos.model.dto.LancamentoResponseDTO;
import com.example.projeto_api_lancamentos.model.enums.TipoTransacaoType;

import java.time.LocalDateTime;

import static com.example.projeto_api_lancamentos.utils.ConstantsUnitTest.ID_PADRAO;
import static com.example.projeto_api_lancamentos.utils.ConstantsUnitTest.VALOR_TRANSACAO;

public class StubLancamento {

    private StubContaCliente cliente = new StubContaCliente();

    public LancamentoResponseDTO montaLancamentoResponse() {
        return new LancamentoResponseDTO(TipoTransacaoType.CREDITO, VALOR_TRANSACAO,  LocalDateTime.of(2024, 5, 1, 14, 0));
    }

    public LancamentoEntity montaLancamento() {
        return new LancamentoEntity(ID_PADRAO,
                TipoTransacaoType.CREDITO,
                VALOR_TRANSACAO,
                cliente.montaEntidade(),
                LocalDateTime.of(2024, 5, 1, 14, 0));
    }
}
