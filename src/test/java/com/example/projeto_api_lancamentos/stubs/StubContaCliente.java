package com.example.projeto_api_lancamentos.stubs;

import com.example.projeto_api_lancamentos.model.ContaClienteEntity;

import static com.example.projeto_api_lancamentos.utils.ConstantsUnitTest.ID_PADRAO;
import static com.example.projeto_api_lancamentos.utils.ConstantsUnitTest.SALDO_BASE;

public class StubContaCliente {
    public ContaClienteEntity montaEntidade(){
        return new ContaClienteEntity(ID_PADRAO, SALDO_BASE);
    }
}
