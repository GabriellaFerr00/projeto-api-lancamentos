package com.example.projeto_api_lancamentos.handler.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrors {
    private Integer status;
    private String message;
    private LocalDateTime timestamp;
}
