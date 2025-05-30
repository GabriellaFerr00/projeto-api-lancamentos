package com.example.projeto_api_lancamentos.handler;

import com.example.projeto_api_lancamentos.handler.exception.RegrasBuscaRepositoryException;
import com.example.projeto_api_lancamentos.handler.exception.RegrasCasoDeUsoException;
import com.example.projeto_api_lancamentos.handler.exception.RegrasDadosDeEntradaException;
import com.example.projeto_api_lancamentos.handler.response.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegrasDadosDeEntradaException.class)
    public ResponseEntity<ApiErrors> handleRegrasDadosDeEntradaException(RegrasDadosDeEntradaException ex) {
        ApiErrors errors = ApiErrors.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegrasCasoDeUsoException.class)
    public ResponseEntity<ApiErrors> handleRegrasCasoDeUsoException(RegrasCasoDeUsoException ex) {
        ApiErrors errors = ApiErrors.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegrasBuscaRepositoryException.class)
    public ResponseEntity<ApiErrors> handleRegrasBuscaRepositoryException(RegrasBuscaRepositoryException ex) {
        ApiErrors errors = ApiErrors.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
