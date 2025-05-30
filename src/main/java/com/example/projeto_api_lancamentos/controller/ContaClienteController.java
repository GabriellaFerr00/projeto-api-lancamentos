package com.example.projeto_api_lancamentos.controller;

import com.example.projeto_api_lancamentos.model.dto.SaldoResponseDTO;
import com.example.projeto_api_lancamentos.model.dto.TransacaoRequestDTO;
import com.example.projeto_api_lancamentos.model.dto.TransacaoResponseDTO;
import com.example.projeto_api_lancamentos.service.ContaClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/conta")
@RequiredArgsConstructor
public class ContaClienteController {

    private final ContaClienteService service;

    @PostMapping("/{idConta}")
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoResponseDTO criartTransacao(@PathVariable("idConta") Long idConta,
                                                @RequestBody TransacaoRequestDTO dto) {
        return service.processarTransacao(idConta, dto);
    }

    @GetMapping("/{idConta}")
    @ResponseStatus(HttpStatus.OK)
    public SaldoResponseDTO saldoDaConta(@PathVariable("idConta") Long idConta) {
        return service.buscarSaldoConta(idConta);
    }
}
