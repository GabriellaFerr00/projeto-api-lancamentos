package com.example.projeto_api_lancamentos.service;

import com.example.projeto_api_lancamentos.handler.Messages;
import com.example.projeto_api_lancamentos.handler.exception.RegrasCasoDeUsoException;
import com.example.projeto_api_lancamentos.handler.exception.RegrasDadosDeEntradaException;
import com.example.projeto_api_lancamentos.model.ContaClienteEntity;
import com.example.projeto_api_lancamentos.model.LancamentoEntity;
import com.example.projeto_api_lancamentos.model.dto.SaldoResponseDTO;
import com.example.projeto_api_lancamentos.model.dto.TransacaoRequestDTO;
import com.example.projeto_api_lancamentos.model.dto.TransacaoResponseDTO;
import com.example.projeto_api_lancamentos.repository.ContaClienteRepository;
import com.example.projeto_api_lancamentos.repository.LancamentoRepository;
import com.example.projeto_api_lancamentos.stubs.StubContaCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.projeto_api_lancamentos.utils.ConstantsUnitTest.ID_PADRAO;
import static com.example.projeto_api_lancamentos.utils.ConstantsUnitTest.SALDO_BASE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContaClienteServiceTest {
    @InjectMocks
    ContaClienteService service;

    @Mock
    ContaClienteRepository repository;

    @Mock
    LancamentoRepository lancamentoRepository;

    private ContaClienteEntity clienteEntity;

    private TransacaoRequestDTO transacaoRequestDTO;

    private TransacaoRequestDTO transacaoBaseDTODebito;

    private TransacaoRequestDTO transacaoBaseDTOCredito;

    private TransacaoRequestDTO transacaoBaseDTODebitoSuperior;

    private TransacaoRequestDTO transacaoBaseDTOCreditoInferior;


    StubContaCliente stubCliente = new StubContaCliente();

    @BeforeEach
    void setUp() {
        this.montaCenarioDeTeste();
    }

    @Test
    @DisplayName("Deve buscar o saldo atual do cliente")
    void deveBuscarLancamentosPorClientId() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(clienteEntity));

        LocalDateTime agora = LocalDateTime.now();
        SaldoResponseDTO dto = service.buscarSaldoConta(ID_PADRAO);

        assertNotNull(dto);
        assertEquals(1L, dto.id());
        assertTrue(dto.dataAtualSaldo().isAfter(agora.minusSeconds(1)));
        assertTrue(dto.dataAtualSaldo().isBefore(agora.plusSeconds(1)));
        assertEquals(SALDO_BASE, dto.saldo());

        verify(repository).findById(ID_PADRAO);
    }

    @Test
    @DisplayName("Deve retornar erro caso passe uma conta incorreta ou nula")
    void deveDarErroComContaIncorreta() {

        RegrasDadosDeEntradaException ex = assertThrows(RegrasDadosDeEntradaException.class, () -> {
            service.buscarSaldoConta(null);
        });

        assertEquals(Messages.ID_INCORRETO, ex.getMessage());
    }

    @Test
    @DisplayName("Deve processar uma lista com as transacoes tipo Debito e Credito com sucesso")
    void deveRetornarUmaListaComDiversasTransacoesNaConta() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(clienteEntity));
        when(lancamentoRepository.save(any())).thenReturn(new LancamentoEntity());

        TransacaoResponseDTO retorno = service.processarTransacao(ID_PADRAO, transacaoRequestDTO);

        assertNotNull(retorno);
        assertEquals(BigDecimal.valueOf(2050.00), retorno.saldoAtual());

        verify(repository).save(clienteEntity);
        verify(lancamentoRepository, times(2)).save(any());
    }

    @Test
    @DisplayName("Deve processar uma lista com as transacoes tipo Debito")
    void deveRetornarUmaListaComTransacoesDeDebitoNaConta() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(clienteEntity));
        when(lancamentoRepository.save(any())).thenReturn(new LancamentoEntity());

        TransacaoResponseDTO retorno = service.processarTransacao(ID_PADRAO, transacaoBaseDTODebito);

        assertNotNull(retorno);
        assertEquals(BigDecimal.valueOf(1950.00), retorno.saldoAtual());

        verify(repository).save(clienteEntity);
        verify(lancamentoRepository).save(any());
    }

    @Test
    @DisplayName("Deve processar uma lista com as transacoes tipo Credito")
    void deveRetornarUmaListaComTransacoesDeCreditoNaConta() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(clienteEntity));
        when(lancamentoRepository.save(any())).thenReturn(new LancamentoEntity());

        TransacaoResponseDTO retorno = service.processarTransacao(ID_PADRAO, transacaoBaseDTOCredito);

        assertNotNull(retorno);
        assertEquals(BigDecimal.valueOf(2100.00), retorno.saldoAtual());

        verify(repository).save(clienteEntity);
        verify(lancamentoRepository).save(any());
    }

    @Test
    @DisplayName("Deve retornar erro caso de saldo insuficiente para debito")
    void deveRetornarErroValorDebitoSuperior() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(clienteEntity));

        RegrasCasoDeUsoException ex = assertThrows(RegrasCasoDeUsoException.class, () -> {
            service.processarTransacao(ID_PADRAO, transacaoBaseDTODebitoSuperior);
        });

        assertTrue(ex.getMessage().contains(Messages.SALDO_INSUFICIENTE + clienteEntity.getSaldo()));

    }

    @Test
    @DisplayName("Deve retornar erro caso de saldo de credito igual a 0")
    void deveRetornarErroValorCreditoZero() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(clienteEntity));

        RegrasDadosDeEntradaException ex = assertThrows(RegrasDadosDeEntradaException.class, () -> {
            service.processarTransacao(ID_PADRAO, transacaoBaseDTOCreditoInferior);
        });

        assertEquals(Messages.VALOR_DEPOSITO_INCORRETO, ex.getMessage());
    }

    @Test
    @DisplayName("Deve retornar erro caso de conta invalida")
    void deveRetornarErroPorContaInvalida() {
        RegrasDadosDeEntradaException ex = assertThrows(RegrasDadosDeEntradaException.class, () -> {
            service.processarTransacao(null, transacaoRequestDTO);
        });

        assertEquals(Messages.CAMPOS_INCORRETOS, ex.getMessage());

    }

    @Test
    @DisplayName("Deve retornar erro caso de dto nulo")
    void deveRetornarErroPorDtoNulo() {

        RegrasDadosDeEntradaException ex = assertThrows(RegrasDadosDeEntradaException.class, () -> {
            service.processarTransacao(ID_PADRAO, null);
        });

        assertEquals(Messages.CAMPOS_INCORRETOS, ex.getMessage());
    }

    @Test
    @DisplayName("Deve retornar erro caso de dto vazio")
    void deveRetornarErroPorDtoVazio() {

        RegrasDadosDeEntradaException ex = assertThrows(RegrasDadosDeEntradaException.class, () -> {
            service.processarTransacao(ID_PADRAO, new TransacaoRequestDTO(List.of()));
        });

        assertEquals(Messages.CAMPOS_INCORRETOS, ex.getMessage());
    }


    private void montaCenarioDeTeste() {
        clienteEntity = stubCliente.montaEntidade();
        transacaoRequestDTO = stubCliente.montaTransacoes();
        transacaoBaseDTOCredito = stubCliente.montaTransacoesCredito();
        transacaoBaseDTODebito = stubCliente.montaTransacoesDebito();
        transacaoBaseDTODebitoSuperior = stubCliente.montaTransacoesDepositoValorSuperior();
        transacaoBaseDTOCreditoInferior = stubCliente.montaTransacoesCreditoZero();
    }

}
