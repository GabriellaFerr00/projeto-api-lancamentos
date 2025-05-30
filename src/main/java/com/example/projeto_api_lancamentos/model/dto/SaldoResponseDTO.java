package com.example.projeto_api_lancamentos.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SaldoResponseDTO(Long id,
                               @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dataAtualSaldo,
                               BigDecimal saldo) {}
