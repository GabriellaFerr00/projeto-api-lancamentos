package com.example.projeto_api_lancamentos.model.dto;

import com.example.projeto_api_lancamentos.model.enums.TipoTransacaoType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LancamentoResponseDTO(TipoTransacaoType tipoTransacao,
                                    BigDecimal valorTransacao,
                                    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dataTransacao) {}
