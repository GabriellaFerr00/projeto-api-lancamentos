package com.example.projeto_api_lancamentos.model.enuns;

import lombok.Getter;

@Getter
public enum TipoTransacaoType {
    DEBITO("D"),
    CREDITO("C");

    private final String valor;

    TipoTransacaoType(String valor){
        this.valor = valor;
    }
}
