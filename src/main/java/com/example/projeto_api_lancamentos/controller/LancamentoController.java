package com.example.projeto_api_lancamentos.controller;

import com.example.projeto_api_lancamentos.model.dto.LancamentoResponseDTO;
import com.example.projeto_api_lancamentos.service.LancamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lancamento")
@RequiredArgsConstructor
public class LancamentoController {

    private final LancamentoService service;

    @GetMapping("/{idConta}")
    @ResponseStatus(HttpStatus.OK)
    public List<LancamentoResponseDTO> buscarLancamentoPorConta(@PathVariable("idConta") Long idConta){
        return service.buscarLancamentoPorCliente(idConta);
    }
}
