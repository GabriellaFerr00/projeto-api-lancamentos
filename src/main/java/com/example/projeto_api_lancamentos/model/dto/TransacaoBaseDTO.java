package com.example.projeto_api_lancamentos.model.dto;

import com.example.projeto_api_lancamentos.model.enums.TipoTransacaoType;

import java.math.BigDecimal;

public record TransacaoBaseDTO(BigDecimal valorTransacao, TipoTransacaoType tipoTransacao) {}
