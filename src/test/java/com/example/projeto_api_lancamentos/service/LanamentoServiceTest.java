package com.example.projeto_api_lancamentos.service;

import com.example.projeto_api_lancamentos.handler.Messages;
import com.example.projeto_api_lancamentos.handler.exception.RegrasBuscaRepositoryException;
import com.example.projeto_api_lancamentos.handler.exception.RegrasDadosDeEntradaException;
import com.example.projeto_api_lancamentos.model.LancamentoEntity;
import com.example.projeto_api_lancamentos.model.dto.LancamentoResponseDTO;
import com.example.projeto_api_lancamentos.model.enums.TipoTransacaoType;
import com.example.projeto_api_lancamentos.repository.LancamentoRepository;
import com.example.projeto_api_lancamentos.stubs.StubLancamento;
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

import static com.example.projeto_api_lancamentos.utils.ConstantsUnitTest.ID_PADRAO;
import static com.example.projeto_api_lancamentos.utils.ConstantsUnitTest.VALOR_TRANSACAO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LanamentoServiceTest {
    @InjectMocks
    LancamentoService service;

    @Mock
    LancamentoRepository repository;

    private LancamentoEntity lancamentoEntity;

    StubLancamento stubLancamento = new StubLancamento();

    @BeforeEach
    void setUp() {
        this.montaCenarioDeTeste();
    }

    @Test
    @DisplayName("Deve buscar no banco os lancamentos pelo id do cliente e retornar uma lista")
    void deveBuscarLancamentosPorClientId() {
        when(repository.findByContaClienteId(any())).thenReturn(List.of(lancamentoEntity));

        List<LancamentoResponseDTO> dtos = service.buscarLancamentoPorCliente(ID_PADRAO);

        assertNotNull(dtos);
        assertEquals(1, dtos.size());
        assertEquals(TipoTransacaoType.CREDITO, dtos.get(0).tipoTransacao());
        assertEquals(VALOR_TRANSACAO, dtos.get(0).valorTransacao());
        assertEquals(LocalDateTime.of(2024, 5, 1, 14, 0), dtos.get(0).dataTransacao());

        verify(repository).findByContaClienteId(ID_PADRAO);
    }

    @Test
    @DisplayName("Deve retornar erro caso passe uma conta incorreta ou nula")
    void deveDarErroComContaIncorreta() {

        RegrasDadosDeEntradaException ex = assertThrows(RegrasDadosDeEntradaException.class, () -> {
            service.buscarLancamentoPorCliente(null);
        });

        assertEquals(Messages.ID_INCORRETO, ex.getMessage());
    }

    @Test
    @DisplayName("Deve retornar erro caso nao ache a conta no banco de dados")
    void deveDarErroComContaInexistente() {

        RegrasBuscaRepositoryException ex = assertThrows(RegrasBuscaRepositoryException.class, () -> {
            service.buscarLancamentoPorCliente(2L);
        });

        assertTrue(ex.getMessage().contains(Messages.CONTA_NAO_ENCONTRADA + 2L));
    }

    @Test
    @DisplayName("Deve converter a entidade para o dto de resposta")
    void deveConverterEntidadeParaDtoResposta() {
        LancamentoResponseDTO dto = service.converteEntidadeParaDTO(lancamentoEntity);

        assertEquals(TipoTransacaoType.CREDITO, dto.tipoTransacao());
        assertEquals(BigDecimal.valueOf(100.00), dto.valorTransacao());
        assertEquals(LocalDateTime.of(2024, 5, 1, 14, 0), dto.dataTransacao());
    }

    private void montaCenarioDeTeste() {
        lancamentoEntity = stubLancamento.montaLancamento();
    }
}
