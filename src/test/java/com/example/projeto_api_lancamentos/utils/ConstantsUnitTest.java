package com.example.projeto_api_lancamentos.utils;

import java.math.BigDecimal;

public interface ConstantsUnitTest {
    Long ID_PADRAO = 1L;
    BigDecimal SALDO_BASE = BigDecimal.valueOf(2000.00);
    BigDecimal VALOR_TRANSACAO = BigDecimal.valueOf(100.00);
    BigDecimal VALOR_TRANSACAO_D = BigDecimal.valueOf(50.00);
    BigDecimal VALOR_DEBITO_SUPERIOR = BigDecimal.valueOf(2100.00);
    BigDecimal VALOR_CREDITO_ZERO = BigDecimal.valueOf(00.00);

}
